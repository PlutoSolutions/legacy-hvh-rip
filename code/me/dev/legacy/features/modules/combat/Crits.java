/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.network.play.client.CPacketUseEntity$Action
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.dev.legacy.features.modules.combat;

import java.util.Objects;
import me.dev.legacy.event.events.PacketEvent;
import me.dev.legacy.features.modules.Module;
import me.dev.legacy.features.setting.Setting;
import me.dev.legacy.util.Timer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Crits
extends Module {
    Setting<Integer> packets = this.register(new Setting<Integer>("Packets", 2, 1, 4));
    private final Timer timer = new Timer();

    public Crits() {
        super("Crits", "Criticals", Module.Category.COMBAT, true, false, false);
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send event) {
        CPacketUseEntity packet;
        if (event.getPacket() instanceof CPacketUseEntity && (packet = (CPacketUseEntity)event.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK) {
            if (!this.timer.passedMs(0L)) {
                return;
            }
            if (Crits.mc.player.onGround && !Crits.mc.gameSettings.keyBindJump.isKeyDown() && packet.getEntityFromWorld((World)Crits.mc.world) instanceof EntityLivingBase && !Crits.mc.player.isInWater() && !Crits.mc.player.isInLava()) {
                switch (this.packets.getValue()) {
                    case 1: {
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY + (double)0.1f, Crits.mc.player.posZ, false));
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                        break;
                    }
                    case 2: {
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY + 0.0625101, Crits.mc.player.posZ, false));
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY + 1.1E-5, Crits.mc.player.posZ, false));
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                        break;
                    }
                    case 3: {
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY + 0.0625101, Crits.mc.player.posZ, false));
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY + 0.0125, Crits.mc.player.posZ, false));
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                        break;
                    }
                    case 4: {
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY + 0.1625, Crits.mc.player.posZ, false));
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY + 4.0E-6, Crits.mc.player.posZ, false));
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY + 1.0E-6, Crits.mc.player.posZ, false));
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                        Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer());
                        Crits.mc.player.onCriticalHit(Objects.requireNonNull(packet.getEntityFromWorld((World)Crits.mc.world)));
                    }
                }
                this.timer.reset();
            }
        }
    }
}

