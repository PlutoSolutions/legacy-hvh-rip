/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 */
package me.dev.legacy.features.modules.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dev.legacy.features.command.Command;
import me.dev.legacy.features.modules.Module;
import me.dev.legacy.features.setting.Setting;

public class MediaModule
extends Module {
    public final Setting<String> NameString = this.register(new Setting<String>("Name", "New Name Here..."));
    private static MediaModule instance;

    public MediaModule() {
        super("Media", "Changes name", Module.Category.CLIENT, false, false, false);
        instance = this;
    }

    @Override
    public void onEnable() {
        Command.sendMessage((Object)ChatFormatting.GRAY + "Success! Name succesfully changed to " + (Object)ChatFormatting.GREEN + this.NameString.getValue());
    }

    public static MediaModule getInstance() {
        if (instance == null) {
            instance = new MediaModule();
        }
        return instance;
    }
}

