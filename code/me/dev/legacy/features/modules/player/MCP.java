/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemEnderPearl
 *  net.minecraft.util.EnumHand
 *  net.minecraft.world.World
 *  org.lwjgl.input.Mouse
 */
package me.dev.legacy.features.modules.player;

import me.dev.legacy.features.modules.Module;
import me.dev.legacy.util.InventoryUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;

public class MCP
extends Module {
    private boolean clicked = false;

    public MCP() {
        super("MCP", "Throws a pearl", Module.Category.PLAYER, false, false, false);
    }

    @Override
    public void onEnable() {
        if (MCP.fullNullCheck()) {
            this.disable();
        }
    }

    @Override
    public void onTick() {
        if (Mouse.isButtonDown((int)2)) {
            if (!this.clicked) {
                this.throwPearl();
            }
            this.clicked = true;
        } else {
            this.clicked = false;
        }
    }

    private void throwPearl() {
        int pearlSlot = InventoryUtil.findHotbarBlock(ItemEnderPearl.class);
        boolean offhand = MCP.mc.player.getHeldItemOffhand().getItem() == Items.ENDER_PEARL;
        boolean bl = offhand;
        if (pearlSlot != -1 || offhand) {
            int oldslot = MCP.mc.player.inventory.currentItem;
            if (!offhand) {
                InventoryUtil.switchToHotbarSlot(pearlSlot, false);
            }
            MCP.mc.playerController.processRightClick((EntityPlayer)MCP.mc.player, (World)MCP.mc.world, offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
            if (!offhand) {
                InventoryUtil.switchToHotbarSlot(oldslot, false);
            }
        }
    }
}

