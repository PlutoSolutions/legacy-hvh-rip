/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 */
package me.dev.legacy.features.modules.misc;

import java.util.Random;
import me.dev.legacy.features.command.Command;
import me.dev.legacy.features.modules.Module;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class Dupe
extends Module {
    private final Random random = new Random();

    public Dupe() {
        super("Dupe", "Ez dupe.", Module.Category.MISC, true, false, false);
    }

    @Override
    public void onEnable() {
        EntityPlayerSP player = Dupe.mc.player;
        WorldClient world = Dupe.mc.world;
        if (player == null || Dupe.mc.world == null) {
            return;
        }
        ItemStack itemStack = player.getHeldItemMainhand();
        if (itemStack.isEmpty()) {
            Command.sendMessage("You need to hold an item in hand to dupe!");
            this.disable();
            return;
        }
        int count = this.random.nextInt(31) + 1;
        for (int i = 0; i <= count; ++i) {
            EntityItem entityItem = player.dropItem(itemStack.copy(), false, true);
            if (entityItem == null) continue;
            world.addEntityToWorld(entityItem.entityId, (Entity)entityItem);
        }
        int total = count * itemStack.getCount();
        player.sendChatMessage("I just used the Legacy Client Dupe and got " + total + " " + itemStack.getDisplayName() + " thanks to Legacy dev's!");
        this.disable();
    }
}

