/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderPearl
 *  net.minecraft.entity.player.EntityPlayer
 */
package me.dev.legacy.features.modules.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.HashMap;
import java.util.UUID;
import me.dev.legacy.Client;
import me.dev.legacy.features.command.Command;
import me.dev.legacy.features.modules.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;

public class PearlNotify
extends Module {
    private final HashMap<EntityPlayer, UUID> list = new HashMap();
    private Entity enderPearl;
    private boolean flag;

    public PearlNotify() {
        super("PearlResolver", "Notify pearl throws.", Module.Category.MISC, true, false, false);
    }

    @Override
    public void onEnable() {
        this.flag = true;
    }

    @Override
    public void onUpdate() {
        if (PearlNotify.mc.world == null || PearlNotify.mc.player == null) {
            return;
        }
        this.enderPearl = null;
        for (Object e : PearlNotify.mc.world.loadedEntityList) {
            if (!(e instanceof EntityEnderPearl)) continue;
            this.enderPearl = e;
            break;
        }
        if (this.enderPearl == null) {
            this.flag = true;
            return;
        }
        EntityPlayer closestPlayer = null;
        for (EntityPlayer entity : PearlNotify.mc.world.playerEntities) {
            if (closestPlayer == null) {
                closestPlayer = entity;
                continue;
            }
            if (closestPlayer.getDistance(this.enderPearl) <= entity.getDistance(this.enderPearl)) continue;
            closestPlayer = entity;
        }
        if (closestPlayer == PearlNotify.mc.player) {
            this.flag = false;
        }
        if (closestPlayer != null && this.flag) {
            String faceing = this.enderPearl.getHorizontalFacing().toString();
            if (faceing.equals("west")) {
                faceing = "east";
            } else if (faceing.equals("east")) {
                faceing = "west";
            }
            Command.sendSilentMessage(Client.friendManager.isFriend(closestPlayer.getName()) ? (Object)ChatFormatting.RED + closestPlayer.getName() + (Object)ChatFormatting.DARK_GRAY + " has just thrown a pearl heading " + faceing + "!" : (Object)ChatFormatting.RED + closestPlayer.getName() + (Object)ChatFormatting.DARK_GRAY + " has just thrown a pearl heading " + faceing + "!");
            this.flag = false;
        }
    }
}

