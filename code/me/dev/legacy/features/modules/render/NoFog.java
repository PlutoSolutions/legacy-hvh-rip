/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.client.event.EntityViewRenderEvent$FogDensity
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.dev.legacy.features.modules.render;

import me.dev.legacy.features.modules.Module;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoFog
extends Module {
    public NoFog() {
        super("NoFog", "Removes fog", Module.Category.RENDER, false, false, false);
    }

    @SubscribeEvent
    public void fog_density(EntityViewRenderEvent.FogDensity event) {
        event.setDensity(0.0f);
        event.setCanceled(true);
    }
}

