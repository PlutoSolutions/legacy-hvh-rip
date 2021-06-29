/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 */
package me.dev.legacy.util;

import me.dev.legacy.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class GuiUtil {
    public static void drawString(String paramString, float paramFloat1, float paramFloat2, int paramInt) {
        if (Client.moduleManager.getModuleByName("CustomFont").isEnabled()) {
            Client.fontRenderer.drawStringWithShadow(paramString, paramFloat1, paramFloat2, paramInt);
        } else {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(paramString, paramFloat1, paramFloat2, paramInt);
        }
    }

    public static int getStringWidth(String paramString) {
        return Minecraft.getMinecraft().fontRenderer.getStringWidth(paramString);
    }

    public static void drawHorizontalLine(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt2 < paramInt1) {
            int i = paramInt1;
            paramInt1 = paramInt2;
            paramInt2 = i;
        }
        GuiUtil.drawRect(paramInt1, paramInt3, paramInt2 + 1, paramInt3 + 1, paramInt4);
    }

    public static void drawString(String paramString, int paramInt1, int paramInt2, int paramInt3) {
        if (Client.moduleManager.getModuleByName("CustomFont").isEnabled()) {
            Client.fontRenderer.drawStringWithShadow(paramString, paramInt1, paramInt2, paramInt3);
        } else {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(paramString, (float)paramInt1, (float)paramInt2, paramInt3);
        }
    }

    public static String getCFont() {
        return Client.fontRenderer.getFont().getFamily();
    }

    public static void drawRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        int i;
        if (paramInt1 < paramInt3) {
            i = paramInt1;
            paramInt1 = paramInt3;
            paramInt3 = i;
        }
        if (paramInt2 < paramInt4) {
            i = paramInt2;
            paramInt2 = paramInt4;
            paramInt4 = i;
        }
        float f1 = (float)(paramInt5 >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(paramInt5 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(paramInt5 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(paramInt5 & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)f2, (float)f3, (float)f4, (float)f1);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos((double)paramInt1, (double)paramInt4, 0.0).endVertex();
        bufferBuilder.pos((double)paramInt3, (double)paramInt4, 0.0).endVertex();
        bufferBuilder.pos((double)paramInt3, (double)paramInt2, 0.0).endVertex();
        bufferBuilder.pos((double)paramInt1, (double)paramInt2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static int getHeight() {
        return Client.moduleManager.getModuleByName("CustomFont").isEnabled() ? Client.fontRenderer.getHeight() : Client.fontRenderer.getHeight();
    }

    public static void drawVerticalLine(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt3 < paramInt2) {
            int i = paramInt2;
            paramInt2 = paramInt3;
            paramInt3 = i;
        }
        GuiUtil.drawRect(paramInt1, paramInt2 + 1, paramInt1 + 1, paramInt3, paramInt4);
    }

    public static void drawCenteredString(String paramString, int paramInt1, int paramInt2, int paramInt3) {
        if (Client.moduleManager.getModuleByName("CustomFont").isEnabled()) {
            Client.fontRenderer.drawStringWithShadow(paramString, paramInt1 - Client.fontRenderer.getStringWidth(paramString) / 2, paramInt2, paramInt3);
        } else {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(paramString, (float)(paramInt1 - Client.fontRenderer.getStringWidth(paramString) / 2), (float)paramInt2, paramInt3);
        }
    }
}

