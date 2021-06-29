/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.entity.player.EntityPlayer
 */
package me.dev.legacy.features.modules.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.HashMap;
import me.dev.legacy.features.command.Command;
import me.dev.legacy.features.modules.Module;
import net.minecraft.entity.player.EntityPlayer;

public class PopCounter
extends Module {
    public static HashMap<String, Integer> TotemPopContainer = new HashMap();
    public static PopCounter INSTANCE = new PopCounter();

    public PopCounter() {
        super("PopCounter", "Counts other players totem pops.", Module.Category.MISC, true, false, false);
        this.setInstance();
    }

    public static PopCounter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PopCounter();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        TotemPopContainer.clear();
    }

    public void onDeath(EntityPlayer player) {
        if (TotemPopContainer.containsKey(player.getName())) {
            int l_Count = TotemPopContainer.get(player.getName());
            TotemPopContainer.remove(player.getName());
            if (l_Count == 1) {
                Command.sendSilentMessage((Object)ChatFormatting.RED + player.getName() + " haha noob died after popping " + (Object)ChatFormatting.RED + l_Count + (Object)ChatFormatting.GREEN + " totem " + (Object)ChatFormatting.RED);
            } else {
                Command.sendSilentMessage((Object)ChatFormatting.RED + player.getName() + " haha noob died after popping " + (Object)ChatFormatting.RED + l_Count + (Object)ChatFormatting.GREEN + " totems " + (Object)ChatFormatting.RED);
            }
        }
    }

    public void onTotemPop(EntityPlayer player) {
        if (PopCounter.fullNullCheck()) {
            return;
        }
        if (PopCounter.mc.player.equals((Object)player)) {
            return;
        }
        int l_Count = 1;
        if (TotemPopContainer.containsKey(player.getName())) {
            l_Count = TotemPopContainer.get(player.getName());
            TotemPopContainer.put(player.getName(), ++l_Count);
        } else {
            TotemPopContainer.put(player.getName(), l_Count);
        }
        if (l_Count == 1) {
            Command.sendSilentMessage((Object)ChatFormatting.RED + player.getName() + " haha noob just popped " + (Object)ChatFormatting.GREEN + l_Count + (Object)ChatFormatting.GREEN + " totem " + (Object)ChatFormatting.RED);
        } else {
            Command.sendSilentMessage((Object)ChatFormatting.RED + player.getName() + " haha noob just popped " + (Object)ChatFormatting.GREEN + l_Count + (Object)ChatFormatting.GREEN + " totems " + (Object)ChatFormatting.RED);
        }
    }
}

