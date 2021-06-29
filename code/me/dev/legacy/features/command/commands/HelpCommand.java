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

public class HelpCommand
extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute(String[] commands) {
        HelpCommand.sendMessage("Commands: ");
        for (Command command : Client.commandManager.getCommands()) {
            HelpCommand.sendMessage((Object)ChatFormatting.GRAY + Client.commandManager.getPrefix() + command.getName());
        }
    }
}

