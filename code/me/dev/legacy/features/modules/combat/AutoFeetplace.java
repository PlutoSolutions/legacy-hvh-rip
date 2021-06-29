/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package me.dev.legacy.features.modules.combat;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.HashMap;
import java.util.Map;
import me.dev.legacy.features.command.Command;
import me.dev.legacy.features.modules.Module;
import me.dev.legacy.features.setting.Setting;
import me.dev.legacy.util.BlockUtil;
import me.dev.legacy.util.BlockUtilll;
import me.dev.legacy.util.ItemUtil;
import me.dev.legacy.util.Timer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoFeetplace
extends Module {
    private final Setting<Integer> delay = this.register(new Setting<Integer>("Delay", 50, 0, 250));
    private final Setting<Integer> blocksPerTick = this.register(new Setting<Integer>("BPT", 8, 1, 20));
    private final Setting<Boolean> helpingBlocks = this.register(new Setting<Boolean>("HelpingBlocks", true));
    private final Setting<Boolean> intelligent = this.register(new Setting<Boolean>("Intelligent", false));
    private final Setting<Boolean> antiPedo = this.register(new Setting<Boolean>("Always Help", false));
    private final Setting<Boolean> floor = this.register(new Setting<Boolean>("Floor", false));
    private final Setting<Integer> retryer = this.register(new Setting<Integer>("Retries", 4, 1, 15));
    private final Setting<Integer> retryDelay = this.register(new Setting<Integer>("Retry Delay", 200, 1, 2500));
    private final Setting<Boolean> existCheck = this.register(new Setting<Boolean>("Exist", false));
    private final Setting<Integer> existed = this.register(new Setting<Integer>("Existed", 4, 1, 15));
    private final Map<BlockPos, Integer> retries = new HashMap<BlockPos, Integer>();
    private final Timer timer = new Timer();
    private final Timer retryTimer = new Timer();
    private boolean didPlace = false;
    private int placements = 0;
    private int obbySlot = -1;
    double posY;

    public AutoFeetplace() {
        super("AutoFeetplace", "Surrounds you with obsidian", Module.Category.COMBAT, true, false, false);
    }

    @Override
    public void onEnable() {
        if (AutoFeetplace.fullNullCheck()) {
            this.setEnabled(false);
            return;
        }
        this.retries.clear();
        this.retryTimer.reset();
        this.posY = AutoFeetplace.mc.player.posY;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        boolean onEChest;
        if (this.check()) {
            return;
        }
        if (this.posY < AutoFeetplace.mc.player.posY) {
            this.setEnabled(false);
            return;
        }
        boolean bl = onEChest = AutoFeetplace.mc.world.getBlockState(new BlockPos(AutoFeetplace.mc.player.getPositionVector())).getBlock() == Blocks.ENDER_CHEST;
        if (AutoFeetplace.mc.player.posY - (double)((int)AutoFeetplace.mc.player.posY) < 0.7) {
            onEChest = false;
        }
        if (!BlockUtil.isSafe((Entity)AutoFeetplace.mc.player, onEChest ? 1 : 0, this.floor.getValue())) {
            this.placeBlocks(AutoFeetplace.mc.player.getPositionVector(), BlockUtil.getUnsafeBlockArray((Entity)AutoFeetplace.mc.player, onEChest ? 1 : 0, this.floor.getValue()), this.helpingBlocks.getValue(), false);
        } else if (!BlockUtil.isSafe((Entity)AutoFeetplace.mc.player, onEChest ? 0 : -1, false) && this.antiPedo.getValue().booleanValue()) {
            this.placeBlocks(AutoFeetplace.mc.player.getPositionVector(), BlockUtil.getUnsafeBlockArray((Entity)AutoFeetplace.mc.player, onEChest ? 0 : -1, false), false, false);
        }
        if (this.didPlace) {
            this.timer.reset();
        }
    }

    private boolean placeBlocks(Vec3d pos, Vec3d[] vec3ds, boolean hasHelpingBlocks, boolean isHelping) {
        int helpings = 0;
        if (this.obbySlot == -1) {
            return false;
        }
        if (AutoFeetplace.mc.player == null) {
            return false;
        }
        int lastSlot = AutoFeetplace.mc.player.inventory.currentItem;
        mc.getConnection().sendPacket((Packet)new CPacketHeldItemChange(this.obbySlot));
        block5: for (Vec3d vec3d : vec3ds) {
            boolean gotHelp = true;
            if (isHelping && !this.intelligent.getValue().booleanValue() && ++helpings > 1) {
                return false;
            }
            BlockPos position = new BlockPos(pos).add(vec3d.x, vec3d.y, vec3d.z);
            switch (BlockUtil.isPositionPlaceable(position, true)) {
                case -1: {
                    continue block5;
                }
                case 2: {
                    if (!hasHelpingBlocks) continue block5;
                    gotHelp = this.placeBlocks(pos, BlockUtil.getHelpingBlocks(vec3d), false, true);
                }
                case 3: {
                    if (gotHelp) {
                        this.placeBlock(position);
                    }
                    if (!isHelping) continue block5;
                    return true;
                }
            }
        }
        mc.getConnection().sendPacket((Packet)new CPacketHeldItemChange(lastSlot));
        return false;
    }

    private boolean check() {
        if (AutoFeetplace.fullNullCheck()) {
            return true;
        }
        this.didPlace = false;
        this.placements = 0;
        this.obbySlot = ItemUtil.getBlockFromHotbar(Blocks.OBSIDIAN);
        if (this.retryTimer.passed(this.retryDelay.getValue().intValue())) {
            this.retries.clear();
            this.retryTimer.reset();
        }
        if (this.obbySlot == -1) {
            this.obbySlot = ItemUtil.getBlockFromHotbar(Blocks.ENDER_CHEST);
            if (this.obbySlot == -1) {
                Command.sendMessage((Object)ChatFormatting.RED + "<AutoFeetPlace> No obsidian.");
                this.setEnabled(false);
                return true;
            }
        }
        return !this.timer.passed(this.delay.getValue().intValue());
    }

    private void placeBlock(BlockPos pos) {
        if (this.placements < this.blocksPerTick.getValue()) {
            BlockUtilll.placeBlock(pos);
            this.didPlace = true;
            ++this.placements;
        }
    }
}

