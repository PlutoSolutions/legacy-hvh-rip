/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package me.dev.legacy.util;

import me.dev.legacy.Client;
import net.minecraft.client.Minecraft;

public class FontUtils {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static float drawStringWithShadow(boolean customFont, String text, int x, int y, int color) {
        if (customFont) {
            return Client.fontRenderer.drawStringWithShadow(text, x, y, color);
        }
        return FontUtils.mc.fontRenderer.drawStringWithShadow(text, (float)x, (float)y, color);
    }

    public static int getStringWidth(boolean customFont, String str) {
        if (customFont) {
            return Client.fontRenderer.getStringWidth(str);
        }
        return FontUtils.mc.fontRenderer.getStringWidth(str);
    }

    public static int getFontHeight(boolean customFont) {
        if (customFont) {
            return Client.fontRenderer.getHeight();
        }
        return FontUtils.mc.fontRenderer.FONT_HEIGHT;
    }

    public static float drawKeyStringWithShadow(boolean customFont, String text, int x, int y, int color) {
        if (customFont) {
            return Client.fontRenderer.drawStringWithShadow(text, x, y, color);
        }
        return FontUtils.mc.fontRenderer.drawStringWithShadow(text, (float)x, (float)y, color);
    }
}

