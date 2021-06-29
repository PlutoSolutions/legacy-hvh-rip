/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 */
package me.dev.legacy.features.modules.movement;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dev.legacy.features.command.Command;
import me.dev.legacy.features.modules.Module;
import me.dev.legacy.features.setting.Setting;

public class AntiVoid
extends Module {
    public Setting<Mode> mode = this.register(new Setting<Mode>("Mode", Mode.BOUNCE));
    public Setting<Boolean> display = this.register(new Setting<Boolean>("Display", true));

    public AntiVoid() {
        super("AntiVoid", "Glitches you up from void.", Module.Category.MOVEMENT, false, false, false);
    }

    @Override
    public void onUpdate() {
        double yLevel = AntiVoid.mc.player.posY;
        if (yLevel <= 0.5) {
            Command.sendMessage((Object)ChatFormatting.RED + "Player " + (Object)ChatFormatting.GREEN + AntiVoid.mc.player.getName() + (Object)ChatFormatting.RED + " is in the void!");
            if (this.mode.getValue().equals((Object)Mode.BOUNCE)) {
                AntiVoid.mc.player.moveVertical = 10.0f;
                AntiVoid.mc.player.jump();
            }
            if (this.mode.getValue().equals((Object)Mode.LAUNCH)) {
                AntiVoid.mc.player.moveVertical = 100.0f;
                AntiVoid.mc.player.jump();
            }
        } else {
            AntiVoid.mc.player.moveVertical = 0.0f;
        }
    }

    @Override
    public void onDisable() {
        AntiVoid.mc.player.moveVertical = 0.0f;
    }

    @Override
    public String getDisplayInfo() {
        if (this.display.getValue().booleanValue()) {
            if (this.mode.getValue().equals((Object)Mode.BOUNCE)) {
                return "Bounce";
            }
            if (this.mode.getValue().equals((Object)Mode.LAUNCH)) {
                return "Launch";
            }
        }
        return null;
    }

    public static enum Mode {
        BOUNCE,
        LAUNCH;

    }
}

