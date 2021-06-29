/*
 * Decompiled with CFR 0.150.
 */
package me.dev.legacy.features.modules.movement;

import me.dev.legacy.features.modules.Module;
import me.dev.legacy.features.setting.Setting;

public class Step
extends Module {
    public Setting<Integer> height = this.register(new Setting<Integer>("Height", 2, 0, 5));

    public Step() {
        super("Step", "Allows you to step up blocks", Module.Category.MOVEMENT, true, false, false);
    }

    @Override
    public void onUpdate() {
        if (Step.fullNullCheck()) {
            return;
        }
        Step.mc.player.stepHeight = 2.0f;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Step.mc.player.stepHeight = 0.6f;
    }
}

