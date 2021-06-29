/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.GameType
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientDisconnectionFromServerEvent
 */
package me.dev.legacy.features.modules.player;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import me.dev.legacy.features.command.Command;
import me.dev.legacy.features.modules.Module;
import me.dev.legacy.features.setting.Setting;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class FakePlayer
extends Module {
    public Setting<String> fakename = this.register(new Setting<String>("Name", "Skitttyy"));
    private EntityOtherPlayerMP clonedPlayer;

    public FakePlayer() {
        super("FakePlayer", "Spawns a literal fake player", Module.Category.PLAYER, false, false, false);
    }

    @Override
    public void onEnable() {
        Command.sendMessage("FakePlayer by the name of " + this.fakename.getValueAsString() + " has been spawned!");
        if (FakePlayer.mc.player == null || FakePlayer.mc.player.isDead) {
            this.disable();
            return;
        }
        this.clonedPlayer = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.fromString("48efc40f-56bf-42c3-aa24-28e0c053f325"), this.fakename.getValueAsString()));
        this.clonedPlayer.copyLocationAndAnglesFrom((Entity)FakePlayer.mc.player);
        this.clonedPlayer.rotationYawHead = FakePlayer.mc.player.rotationYawHead;
        this.clonedPlayer.rotationYaw = FakePlayer.mc.player.rotationYaw;
        this.clonedPlayer.rotationPitch = FakePlayer.mc.player.rotationPitch;
        this.clonedPlayer.setGameType(GameType.SURVIVAL);
        this.clonedPlayer.setHealth(20.0f);
        FakePlayer.mc.world.addEntityToWorld(-12345, (Entity)this.clonedPlayer);
        this.clonedPlayer.onLivingUpdate();
    }

    @Override
    public void onDisable() {
        if (FakePlayer.mc.world != null) {
            FakePlayer.mc.world.removeEntityFromWorld(-12345);
        }
    }

    @SubscribeEvent
    public void onClientDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        if (this.isEnabled()) {
            this.disable();
        }
    }
}

