/*
 * Decompiled with CFR 0.150.
 */
package me.dev.legacy.features.modules.movement;

import me.dev.legacy.features.modules.Module;

public class ReverseStep
extends Module {
    private static ReverseStep INSTANCE = new ReverseStep();

    public ReverseStep() {
        super("ReverseStep", "ReverseStep.", Module.Category.MOVEMENT, true, false, false);
        this.setInstance();
    }

    public static ReverseStep getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReverseStep();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void onUpdate() {
        if (ReverseStep.mc.player == null || ReverseStep.mc.world == null || ReverseStep.mc.player.isInWater() || ReverseStep.mc.player.isInLava()) {
            return;
        }
        if (ReverseStep.mc.player.onGround) {
            ReverseStep.mc.player.motionY -= 1.0;
        }
    }
}

