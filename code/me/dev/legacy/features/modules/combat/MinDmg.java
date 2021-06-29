/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 */
package me.dev.legacy.features.modules.combat;

import com.google.common.eventbus.Subscribe;
import me.dev.legacy.features.modules.Module;
import me.dev.legacy.features.modules.combat.AutoCrystal;
import me.dev.legacy.features.setting.Setting;

public class MinDmg
extends Module {
    private static MinDmg INSTANCE = new MinDmg();
    private final Setting<Float> EnableDamage = this.register(new Setting<Float>("Enable MinDmg", Float.valueOf(4.0f), Float.valueOf(1.0f), Float.valueOf(36.0f)));
    private final Setting<Float> DisableDamage = this.register(new Setting<Float>("Disable MinDmg", Float.valueOf(4.0f), Float.valueOf(1.0f), Float.valueOf(36.0f)));

    public MinDmg() {
        super("MinDmg", "Set minimal damage for auto crystal.", Module.Category.COMBAT, true, false, false);
        INSTANCE = this;
    }

    public static MinDmg getInstance() {
        return INSTANCE;
    }

    @Override
    @Subscribe
    public void onEnable() {
        AutoCrystal.getInstance().minDamage.setValue(this.EnableDamage.getValue());
    }

    @Override
    @Subscribe
    public void onDisable() {
        AutoCrystal.getInstance().minDamage.setValue(this.DisableDamage.getValue());
    }
}

