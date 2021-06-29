/*
 * Decompiled with CFR 0.150.
 */
package me.dev.legacy.features.modules.movement;

import me.dev.legacy.features.modules.Module;

public class Speed
extends Module {
    public Speed() {
        super("Speed", "placeholder", Module.Category.MOVEMENT, false, false, false);
    }

    @Override
    public String getDisplayInfo() {
        return "Strafe";
    }
}

