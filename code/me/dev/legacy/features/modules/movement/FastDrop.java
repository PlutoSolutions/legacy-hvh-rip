/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.dev.legacy.features.modules.movement;

import me.dev.legacy.features.modules.Module;
import me.dev.legacy.features.setting.Setting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FastDrop
extends Module {
    private final Setting<Float> speed = this.register(new Setting<Float>("Speed", Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(20.0f)));

    public FastDrop() {
        super("FastDrop", "Fast drop to ffa.", Module.Category.MOVEMENT, false, false, false);
    }

    @Override
    @SubscribeEvent
    public void onUpdate() {
        if (FastDrop.mc.player == null || FastDrop.mc.world == null || FastDrop.mc.player.isInWater() || FastDrop.mc.player.isInLava()) {
            return;
        }
        if (FastDrop.mc.player.onGround) {
            FastDrop.mc.player.motionY -= (double)(this.speed.getValue().floatValue() / 10.0f);
        }
    }
}

