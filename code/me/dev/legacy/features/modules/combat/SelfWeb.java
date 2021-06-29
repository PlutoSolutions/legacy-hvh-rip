/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 */
package me.dev.legacy.features.modules.combat;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dev.legacy.features.command.Command;
import me.dev.legacy.features.modules.Module;
import me.dev.legacy.features.setting.Setting;
import me.dev.legacy.util.BlockInteractionUtil;
import me.dev.legacy.util.EntityUtil;
import me.dev.legacy.util.WorldUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class SelfWeb
extends Module {
    public Setting<Boolean> alwayson = this.register(new Setting<Boolean>("AlwaysOn", false));
    public Setting<Boolean> rotate = this.register(new Setting<Boolean>("Rotate", false));
    public Setting<Integer> webRange = this.register(new Setting<Integer>("EnemyRange", 4, 0, 8));
    int new_slot = -1;
    boolean sneak = false;

    public SelfWeb() {
        super("SelfWeb", "Places webs at your feet", Module.Category.COMBAT, false, false, false);
    }

    @Override
    public void enable() {
        if (SelfWeb.mc.player != null) {
            this.new_slot = this.find_in_hotbar();
            if (this.new_slot == -1) {
                Command.sendMessage((Object)ChatFormatting.RED + "< " + (Object)ChatFormatting.GRAY + "SelfWeb" + (Object)ChatFormatting.RED + "> " + (Object)ChatFormatting.DARK_RED + "No webs in hotbar!");
            }
        }
    }

    @Override
    public void disable() {
        if (SelfWeb.mc.player != null && this.sneak) {
            SelfWeb.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)SelfWeb.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.sneak = false;
        }
    }

    @Override
    public void onUpdate() {
        if (SelfWeb.mc.player == null) {
            return;
        }
        if (this.alwayson.getValue().booleanValue()) {
            EntityPlayer target = this.find_closest_target();
            if (target == null) {
                return;
            }
            if (SelfWeb.mc.player.getDistance((Entity)target) < (float)this.webRange.getValue().intValue() && this.is_surround()) {
                int last_slot = SelfWeb.mc.player.inventory.currentItem;
                SelfWeb.mc.player.inventory.currentItem = this.new_slot;
                SelfWeb.mc.playerController.updateController();
                this.place_blocks(WorldUtil.GetLocalPlayerPosFloored());
                SelfWeb.mc.player.inventory.currentItem = last_slot;
            }
        } else {
            int last_slot = SelfWeb.mc.player.inventory.currentItem;
            SelfWeb.mc.player.inventory.currentItem = this.new_slot;
            SelfWeb.mc.playerController.updateController();
            this.place_blocks(WorldUtil.GetLocalPlayerPosFloored());
            SelfWeb.mc.player.inventory.currentItem = last_slot;
            this.disable();
        }
    }

    public EntityPlayer find_closest_target() {
        if (SelfWeb.mc.world.playerEntities.isEmpty()) {
            return null;
        }
        EntityPlayer closestTarget = null;
        for (EntityPlayer target : SelfWeb.mc.world.playerEntities) {
            if (target == SelfWeb.mc.player || EntityUtil.isLiving((Entity)target) || target.getHealth() <= 0.0f || closestTarget != null && SelfWeb.mc.player.getDistance((Entity)target) > SelfWeb.mc.player.getDistance((Entity)closestTarget)) continue;
            closestTarget = target;
        }
        return closestTarget;
    }

    private int find_in_hotbar() {
        for (int i = 0; i < 9; ++i) {
            ItemStack stack = SelfWeb.mc.player.inventory.getStackInSlot(i);
            if (stack.getItem() != Item.getItemById((int)30)) continue;
            return i;
        }
        return -1;
    }

    private boolean is_surround() {
        BlockPos player_block = WorldUtil.GetLocalPlayerPosFloored();
        return SelfWeb.mc.world.getBlockState(player_block.east()).getBlock() != Blocks.AIR && SelfWeb.mc.world.getBlockState(player_block.west()).getBlock() != Blocks.AIR && SelfWeb.mc.world.getBlockState(player_block.north()).getBlock() != Blocks.AIR && SelfWeb.mc.world.getBlockState(player_block.south()).getBlock() != Blocks.AIR && SelfWeb.mc.world.getBlockState(player_block).getBlock() == Blocks.AIR;
    }

    private void place_blocks(BlockPos pos) {
        if (!SelfWeb.mc.world.getBlockState(pos).getMaterial().isReplaceable()) {
            return;
        }
        if (!BlockInteractionUtil.checkForNeighbours(pos)) {
            return;
        }
        for (EnumFacing side : EnumFacing.values()) {
            BlockPos neighbor = pos.offset(side);
            EnumFacing side2 = side.getOpposite();
            if (!BlockInteractionUtil.canBeClicked(neighbor)) continue;
            if (BlockInteractionUtil.blackList.contains((Object)SelfWeb.mc.world.getBlockState(neighbor).getBlock())) {
                SelfWeb.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)SelfWeb.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                this.sneak = true;
            }
            Vec3d hitVec = new Vec3d((Vec3i)neighbor).add(0.5, 0.5, 0.5).add(new Vec3d(side2.getDirectionVec()).scale(0.5));
            if (this.rotate.getValue().booleanValue()) {
                BlockInteractionUtil.faceVectorPacketInstant(hitVec);
            }
            SelfWeb.mc.playerController.processRightClickBlock(SelfWeb.mc.player, SelfWeb.mc.world, neighbor, side2, hitVec, EnumHand.MAIN_HAND);
            SelfWeb.mc.player.swingArm(EnumHand.MAIN_HAND);
            return;
        }
    }
}

