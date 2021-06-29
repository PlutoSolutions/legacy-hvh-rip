/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 */
package me.dev.legacy.features.modules.combat;

import me.dev.legacy.features.modules.Module;
import me.dev.legacy.features.setting.Setting;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class AutoLog
extends Module {
    public final Setting<Boolean> settingPacketKick = this.register(new Setting<Boolean>("Packet Kick", false));
    public final Setting<Integer> settingHealth = this.register(new Setting<Integer>("Health", 6, 1, 20));

    public AutoLog() {
        super("AutoLog", "Automatically logs on combat.", Module.Category.COMBAT, true, false, false);
    }

    @Override
    public void onTick() {
        if (AutoLog.mc.player == null || AutoLog.mc.world == null || AutoLog.mc.player.capabilities.isCreativeMode) {
            return;
        }
        float health = AutoLog.mc.player.getHealth();
        if (health <= (float)this.settingHealth.getValue().intValue() && health != 0.0f && !AutoLog.mc.player.isDead) {
            this.doLog();
            this.toggle();
        }
    }

    public void doLog() {
        if (this.settingPacketKick.getValue().booleanValue()) {
            AutoLog.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(AutoLog.mc.player.posX, AutoLog.mc.player.posY + 50.0, AutoLog.mc.player.posZ, false));
        }
        AutoLog.mc.player.connection.getNetworkManager().closeChannel((ITextComponent)new TextComponentString("Auto Log!"));
    }
}

