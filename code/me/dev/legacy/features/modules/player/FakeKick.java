/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.network.play.server.SPacketDisconnect
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 */
package me.dev.legacy.features.modules.player;

import me.dev.legacy.features.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class FakeKick
extends Module {
    public FakeKick() {
        super("FakeKick", "Say you were ddosed (with the clip!)", Module.Category.PLAYER, true, false, false);
    }

    @Override
    public void onEnable() {
        Minecraft.getMinecraft().getConnection().handleDisconnect(new SPacketDisconnect((ITextComponent)new TextComponentString("Internal Exception: java.lang.NullPointerException")));
        this.disable();
    }
}

