/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 */
package me.dev.legacy.features.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dev.legacy.Client;
import me.dev.legacy.features.command.Command;

public class PrefixCommand
extends Command {
    public PrefixCommand() {
        super("prefix", new String[]{"<char>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            Command.sendMessage((Object)ChatFormatting.GREEN + "Current prefix is " + Client.commandManager.getPrefix());
            return;
        }
        Client.commandManager.setPrefix(commands[0]);
        Command.sendMessage("Prefix changed to " + (Object)ChatFormatting.GRAY + commands[0]);
    }
}

