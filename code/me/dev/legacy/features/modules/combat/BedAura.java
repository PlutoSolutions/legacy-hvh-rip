/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.EnumHand
 */
package me.dev.legacy.features.modules.combat;

import me.dev.legacy.features.modules.Module;
import me.dev.legacy.features.setting.Setting;
import me.dev.legacy.manager.RotationManager;
import net.minecraft.util.EnumHand;

public class BedAura
extends Module {
    public final Setting<Boolean> settingAntiNaked = this.register(new Setting<Boolean>("Anti-Naked", true));
    public final Setting<Boolean> settingAutoSwitch = this.register(new Setting<Boolean>("Auto-Switch", true));
    public final Setting<Boolean> settingAutoOffhand = this.register(new Setting<Boolean>("Auto-Offhand", false));
    public final Setting<Float> settingRange = this.register(new Setting<Float>("Range", Float.valueOf(6.0f), Float.valueOf(1.0f), Float.valueOf(13.0f)));
    public final Setting<Enum> settingTargetMode = this.register(new Setting<TargetMode>("Target Mode", TargetMode.UNSAFE));
    public final Setting<Boolean> settingSuicide = this.register(new Setting<Boolean>("Suicide", false));
    public final Setting<Integer> settingSelfDamage = this.register(new Setting<Integer>("Self Damage", 8, 1, 36));
    public final Setting<Float> settingMinimumDamageTarget = this.register(new Setting<Float>("Min. Target Dmg.", Float.valueOf(2.0f), Float.valueOf(1.0f), Float.valueOf(36.0f)));
    public final Setting<Boolean> settingPlace = this.register(new Setting<Boolean>("Place", true));
    public final Setting<Boolean> settingPlaceSync = this.register(new Setting<Boolean>("Place Sync", true));
    public final Setting<Boolean> settingPlaceWorldTick = this.register(new Setting<Boolean>("Place World Tick", false));
    public final Setting<Boolean> settingAirPlace = this.register(new Setting<Boolean>("Air Place", false));
    public final Setting<Float> settingPlaceRange = this.register(new Setting<Float>("Place Range", Float.valueOf(4.0f), Float.valueOf(1.0f), Float.valueOf(6.0f)));
    public final Setting<Integer> settingPlaceDelay = this.register(new Setting<Integer>("Place Delay", 50, 0, 100));
    public final Setting<Enum> settingPlaceRotate = this.register(new Setting<RotationManager.Rotation>("Place Rotate", RotationManager.Rotation.SEND));
    public final Setting<Boolean> settingClick = this.register(new Setting<Boolean>("Click", true));
    public final Setting<Boolean> settingClickPredict = this.register(new Setting<Boolean>("Click Predict", false));
    public final Setting<Boolean> settingClickSync = this.register(new Setting<Boolean>("Click Sync", true));
    public final Setting<Boolean> settingClickOnlyWhenEquippedBed = this.register(new Setting<Boolean>("Click With Bed", false));
    public final Setting<Boolean> settingClickWorldTick = this.register(new Setting<Boolean>("Click World Tick", false));
    public final Setting<Enum> settingClickHand = this.register(new Setting<ClickHand>("Click Hand", ClickHand.AUTO));
    public final Setting<Float> settingClickRange = this.register(new Setting<Float>("Click Range", Float.valueOf(4.0f), Float.valueOf(1.0f), Float.valueOf(6.0f)));
    public final Setting<Integer> settingClickDelay = this.register(new Setting<Integer>("Click Delay", 50, 0, 100));
    public final Setting<Enum> settingClickRotate = this.register(new Setting<RotationManager.Rotation>("Click Rotate", RotationManager.Rotation.SEND));
    public final Setting<Boolean> settingRenderSwing = this.register(new Setting<Boolean>("Render Swing", true));
    public final Setting<Boolean> settingRGB = this.register(new Setting<Boolean>("RGB", false));
    public final Setting<Integer> settingRed = this.register(new Setting<Integer>("Red", 255, 0, 255));
    public final Setting<Integer> settingGreen = this.register(new Setting<Integer>("Green", 0, 0, 255));
    public final Setting<Integer> settingBlue = this.register(new Setting<Integer>("Blue", 255, 0, 255));
    public final Setting<Integer> settingAlpha = this.register(new Setting<Integer>("Alpha", 255, 0, 255));
    public final Setting<Float> settingOutlineLineSize = this.register(new Setting<Float>("Outline Line Size", Float.valueOf(1.0f), Float.valueOf(1.0f), Float.valueOf(3.0f)));
    public final Setting<Integer> settingOutlineAlpha = this.register(new Setting<Integer>("Outline Alpha", 255, 0, 255));

    public BedAura() {
        super("Aura", "Kills aura.", Module.Category.COMBAT, true, false, false);
    }

    public static enum ClickHand {
        AUTO(null),
        OFF(EnumHand.OFF_HAND),
        MAIN(EnumHand.MAIN_HAND);

        EnumHand hand;

        private ClickHand(EnumHand hand) {
            this.hand = hand;
        }

        public EnumHand getHand() {
            return this.hand;
        }
    }

    public static enum TargetMode {
        UNSAFE,
        CLOSET;

    }
}

