/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemSword
 *  net.minecraft.util.EnumHand
 */
package me.dev.legacy.features.modules.render;

import me.dev.legacy.features.modules.Module;
import me.dev.legacy.features.setting.Setting;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;

public class Swing
extends Module {
    private Setting<Hand> hand = this.register(new Setting<Hand>("Hand", Hand.OFFHAND));

    public Swing() {
        super("Swing", "Changes the hand you swing with", Module.Category.RENDER, false, false, false);
    }

    @Override
    public void onUpdate() {
        if (Swing.mc.world == null) {
            return;
        }
        if (this.hand.getValue().equals((Object)Hand.OFFHAND)) {
            Swing.mc.player.swingingHand = EnumHand.OFF_HAND;
        }
        if (this.hand.getValue().equals((Object)Hand.MAINHAND)) {
            Swing.mc.player.swingingHand = EnumHand.MAIN_HAND;
        }
        if (this.hand.getValue().equals((Object)Hand.PACKETSWING) && Swing.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword && (double)Swing.mc.entityRenderer.itemRenderer.prevEquippedProgressMainHand >= 0.9) {
            Swing.mc.entityRenderer.itemRenderer.equippedProgressMainHand = 1.0f;
            Swing.mc.entityRenderer.itemRenderer.itemStackMainHand = Swing.mc.player.getHeldItemMainhand();
        }
    }

    public static enum Hand {
        OFFHAND,
        MAINHAND,
        PACKETSWING;

    }
}

