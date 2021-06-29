/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemExpBottle
 */
package me.dev.legacy.features.modules.player;

import me.dev.legacy.features.modules.Module;
import me.dev.legacy.features.setting.Setting;
import me.dev.legacy.util.InventoryUtil;
import net.minecraft.item.ItemExpBottle;

public class FastPlace
extends Module {
    private Setting<Item> Items = this.register(new Setting<Item>("Items", Item.XP));

    public FastPlace() {
        super("FastPlace", "Fast everything.", Module.Category.PLAYER, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (FastPlace.fullNullCheck()) {
            return;
        }
        if (this.Items.getValue().equals((Object)Item.XP)) {
            if (this.Items.getValue().equals((Object)Item.XP) && InventoryUtil.holdingItem(ItemExpBottle.class)) {
                FastPlace.mc.rightClickDelayTimer = 0;
            }
        } else {
            FastPlace.mc.rightClickDelayTimer = 0;
        }
    }

    private static enum Item {
        XP,
        ALL;

    }
}

