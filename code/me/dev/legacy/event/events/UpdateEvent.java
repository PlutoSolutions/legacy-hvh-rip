/*
 * Decompiled with CFR 0.150.
 */
package me.dev.legacy.event.events;

import me.dev.legacy.event.EventStage;

public class UpdateEvent
extends EventStage {
    private final int stage;

    public UpdateEvent(int stage) {
        this.stage = stage;
    }

    @Override
    public final int getStage() {
        return this.stage;
    }
}

