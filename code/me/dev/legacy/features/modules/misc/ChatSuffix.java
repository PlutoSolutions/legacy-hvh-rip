/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.client.event.ClientChatEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.dev.legacy.features.modules.misc;

import me.dev.legacy.features.modules.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSuffix
extends Module {
    public ChatSuffix() {
        super("ChatSuffix", "Appends your message", Module.Category.MISC, true, false, false);
    }

    @Override
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        String SyndiHaxSuffix = " \u029f\u1d07\u0262\u1d00\u1d04\u028f";
        if (event.getMessage().startsWith("/") || event.getMessage().startsWith(".") || event.getMessage().startsWith(",") || event.getMessage().startsWith("-") || event.getMessage().startsWith("$") || event.getMessage().startsWith("*")) {
            return;
        }
        event.setMessage(event.getMessage() + SyndiHaxSuffix);
    }

    @Override
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
}

