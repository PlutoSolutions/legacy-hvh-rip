/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.AttackEntityEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent
 */
package me.dev.legacy.features.modules.render;

import java.awt.Color;
import me.dev.legacy.event.events.Render2DEvent;
import me.dev.legacy.features.modules.Module;
import me.dev.legacy.features.setting.Setting;
import me.dev.legacy.util.RenderUtil;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public final class HitMarkers
extends Module {
    public final ResourceLocation image;
    private int renderTicks = 100;
    public Setting<Integer> red = this.register(new Setting<Integer>("Red", 255, 0, 255));
    public Setting<Integer> green = this.register(new Setting<Integer>("Green", 255, 0, 255));
    public Setting<Integer> blue = this.register(new Setting<Integer>("Blue", 255, 0, 255));
    public Setting<Integer> alpha = this.register(new Setting<Integer>("Alpha", 255, 0, 255));
    public Setting<Integer> thickness = this.register(new Setting<Integer>("Thickness", 2, 1, 6));
    public Setting<Double> time = this.register(new Setting<Double>("Time", 20.0, 1.0, 50.0));

    public HitMarkers() {
        super("HitMarkers", "hitmarker thingys", Module.Category.RENDER, false, false, false);
        this.image = new ResourceLocation("hitmarker.png");
    }

    @Override
    public void onRender2D(Render2DEvent event) {
        if ((double)this.renderTicks < this.time.getValue()) {
            ScaledResolution resolution = new ScaledResolution(mc);
            this.drawHitMarkers();
        }
    }

    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event) {
        if (!event.getEntity().equals((Object)HitMarkers.mc.player)) {
            return;
        }
        this.renderTicks = 0;
    }

    @SubscribeEvent
    public void onTickClientTick(TickEvent event) {
        ++this.renderTicks;
    }

    public void drawHitMarkers() {
        ScaledResolution resolution = new ScaledResolution(mc);
        RenderUtil.drawLine((float)resolution.getScaledWidth() / 2.0f - 4.0f, (float)resolution.getScaledHeight() / 2.0f - 4.0f, (float)resolution.getScaledWidth() / 2.0f - 8.0f, (float)resolution.getScaledHeight() / 2.0f - 8.0f, this.thickness.getValue().intValue(), new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue()).getRGB());
        RenderUtil.drawLine((float)resolution.getScaledWidth() / 2.0f + 4.0f, (float)resolution.getScaledHeight() / 2.0f - 4.0f, (float)resolution.getScaledWidth() / 2.0f + 8.0f, (float)resolution.getScaledHeight() / 2.0f - 8.0f, this.thickness.getValue().intValue(), new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue()).getRGB());
        RenderUtil.drawLine((float)resolution.getScaledWidth() / 2.0f - 4.0f, (float)resolution.getScaledHeight() / 2.0f + 4.0f, (float)resolution.getScaledWidth() / 2.0f - 8.0f, (float)resolution.getScaledHeight() / 2.0f + 8.0f, this.thickness.getValue().intValue(), new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue()).getRGB());
        RenderUtil.drawLine((float)resolution.getScaledWidth() / 2.0f + 4.0f, (float)resolution.getScaledHeight() / 2.0f + 4.0f, (float)resolution.getScaledWidth() / 2.0f + 8.0f, (float)resolution.getScaledHeight() / 2.0f + 8.0f, this.thickness.getValue().intValue(), new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue()).getRGB());
    }
}

