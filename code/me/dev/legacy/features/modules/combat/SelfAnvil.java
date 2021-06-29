/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockAnvil
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 */
package me.dev.legacy.features.modules.combat;

import me.dev.legacy.features.modules.Module;
import me.dev.legacy.features.setting.Setting;
import me.dev.legacy.util.BlockUtil;
import me.dev.legacy.util.EntityUtil;
import me.dev.legacy.util.InventoryUtil;
import me.dev.legacy.util.PlayerUtil;
import net.minecraft.block.BlockAnvil;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class SelfAnvil
extends Module {
    Setting<Integer> ammount = this.register(new Setting<Integer>("Ammount", 1, 1, 2));
    Setting<Boolean> rotate = this.register(new Setting<Boolean>("Rotate", false));
    private int placedAmmount;

    public SelfAnvil() {
        super("SelfAnvil", "Drops anvil on you.", Module.Category.COMBAT, true, false, false);
    }

    @Override
    public void onEnable() {
        this.placedAmmount = 0;
        if (InventoryUtil.findHotbarBlock(BlockAnvil.class) == -1 || PlayerUtil.findObiInHotbar() == -1) {
            this.disable();
        }
    }

    @Override
    public void onTick() {
        EntityPlayerSP target = SelfAnvil.mc.player;
        if (target == null || this.placedAmmount >= this.ammount.getValue()) {
            return;
        }
        BlockPos anvilPos = EntityUtil.getFlooredPos((Entity)target).up(2);
        if (BlockUtil.canPlaceBlock(anvilPos)) {
            this.placeAnvil(anvilPos);
            ++this.placedAmmount;
        } else {
            this.placeObi(anvilPos.down().east());
            this.placeObi(anvilPos.east());
        }
    }

    private void placeAnvil(BlockPos pos) {
        int old = SelfAnvil.mc.player.inventory.currentItem;
        this.switchToSlot(InventoryUtil.findHotbarBlock(BlockAnvil.class));
        BlockUtil.placeBlock(pos, EnumHand.MAIN_HAND, this.rotate.getValue(), true, false);
        this.switchToSlot(old);
        this.toggle();
    }

    private void placeObi(BlockPos pos) {
        int old = SelfAnvil.mc.player.inventory.currentItem;
        this.switchToSlot(PlayerUtil.findObiInHotbar());
        BlockUtil.placeBlock(pos, EnumHand.MAIN_HAND, this.rotate.getValue(), true, false);
        this.switchToSlot(old);
    }

    private void switchToSlot(int slot) {
        SelfAnvil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
        SelfAnvil.mc.player.inventory.currentItem = slot;
        SelfAnvil.mc.playerController.updateController();
    }
}

