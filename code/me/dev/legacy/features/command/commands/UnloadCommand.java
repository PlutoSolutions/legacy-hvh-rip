/*
 * Decompiled with CFR 0.150.
 */
package me.dev.legacy.features.command.commands;

import me.dev.legacy.Client;
import me.dev.legacy.features.command.Command;

public class UnloadCommand
extends Command {
    public UnloadCommand() {
        super("unload", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        Client.unload(true);
    }
}

