/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  org.lwjgl.input.Mouse
 */
package me.dev.legacy.features.modules.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dev.legacy.Client;
import me.dev.legacy.features.command.Command;
import me.dev.legacy.features.modules.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import org.lwjgl.input.Mouse;

public class MCF
extends Module {
    private boolean clicked = false;

    public MCF() {
        super("MCF", "Middleclick Friends.", Module.Category.MISC, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (Mouse.isButtonDown((int)2)) {
            if (!this.clicked && MCF.mc.currentScreen == null) {
                this.onClick();
            }
            this.clicked = true;
        } else {
            this.clicked = false;
        }
    }

    private void onClick() {
        Entity entity;
        RayTraceResult result = MCF.mc.objectMouseOver;
        if (result != null && result.typeOfHit == RayTraceResult.Type.ENTITY && (entity = result.entityHit) instanceof EntityPlayer) {
            if (Client.friendManager.isFriend(entity.getName())) {
                Client.friendManager.removeFriend(entity.getName());
                Command.sendMessage((Object)ChatFormatting.RED + entity.getName() + (Object)ChatFormatting.RED + " has been unfriended.");
            } else {
                Client.friendManager.addFriend(entity.getName());
                Command.sendMessage((Object)ChatFormatting.AQUA + entity.getName() + (Object)ChatFormatting.AQUA + " has been friended.");
            }
        }
        this.clicked = true;
    }
}

