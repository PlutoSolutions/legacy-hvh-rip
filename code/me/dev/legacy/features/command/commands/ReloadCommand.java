/*
 * Decompiled with CFR 0.150.
 */
package me.dev.legacy.features.command.commands;

import me.dev.legacy.Client;
import me.dev.legacy.features.command.Command;

public class ReloadCommand
extends Command {
    public ReloadCommand() {
        super("reload", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        Client.reload();
    }
}

