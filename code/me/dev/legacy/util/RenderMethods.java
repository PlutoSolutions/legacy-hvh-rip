/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.math.AxisAlignedBB
 *  org.lwjgl.opengl.GL11
 */
package me.dev.legacy.util;

import java.awt.Color;
import java.awt.Rectangle;
import java.nio.ByteBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class RenderMethods {
    public static void drawGradientRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt1, int paramInt2) {
        RenderMethods.enableGL2D();
        GL11.glShadeModel((int)7425);
        GL11.glBegin((int)7);
        RenderMethods.glColor(paramInt1);
        GL11.glVertex2f((float)paramFloat1, (float)paramFloat4);
        GL11.glVertex2f((float)paramFloat3, (float)paramFloat4);
        RenderMethods.glColor(paramInt2);
        GL11.glVertex2f((float)paramFloat3, (float)paramFloat2);
        GL11.glVertex2f((float)paramFloat1, (float)paramFloat2);
        GL11.glEnd();
        GL11.glShadeModel((int)7424);
        RenderMethods.disableGL2D();
    }

    public static void drawBorderedRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt1, int paramInt2) {
        RenderMethods.enableGL2D();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        RenderMethods.drawVLine(paramFloat1 *= 2.0f, paramFloat2 *= 2.0f, (paramFloat4 *= 2.0f) - 1.0f, paramInt2);
        RenderMethods.drawVLine((paramFloat3 *= 2.0f) - 1.0f, paramFloat2, paramFloat4, paramInt2);
        RenderMethods.drawHLine(paramFloat1, paramFloat3 - 1.0f, paramFloat2, paramInt2);
        RenderMethods.drawHLine(paramFloat1, paramFloat3 - 2.0f, paramFloat4 - 1.0f, paramInt2);
        RenderMethods.drawRect(paramFloat1 + 1.0f, paramFloat2 + 1.0f, paramFloat3 - 1.0f, paramFloat4 - 1.0f, paramInt1);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        RenderMethods.disableGL2D();
    }

    public static void drawOutlinedBox(AxisAlignedBB paramAxisAlignedBB) {
        if (paramAxisAlignedBB == null) {
            return;
        }
        GL11.glBegin((int)3);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glEnd();
        GL11.glBegin((int)3);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glEnd();
        GL11.glBegin((int)1);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glEnd();
    }

    public static void drawBorderedRectReliant(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, int paramInt1, int paramInt2) {
        RenderMethods.enableGL2D();
        RenderMethods.drawRect(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt1);
        RenderMethods.glColor(paramInt2);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glLineWidth((float)paramFloat5);
        GL11.glBegin((int)3);
        GL11.glVertex2f((float)paramFloat1, (float)paramFloat2);
        GL11.glVertex2f((float)paramFloat1, (float)paramFloat4);
        GL11.glVertex2f((float)paramFloat3, (float)paramFloat4);
        GL11.glVertex2f((float)paramFloat3, (float)paramFloat2);
        GL11.glVertex2f((float)paramFloat1, (float)paramFloat2);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        RenderMethods.disableGL2D();
    }

    public static void drawStrip(int paramInt1, int paramInt2, float paramFloat1, double paramDouble, float paramFloat2, float paramFloat3, int paramInt3) {
        float f7;
        float f6;
        float f5;
        int b;
        float f1 = (float)(paramInt3 >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(paramInt3 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(paramInt3 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(paramInt3 & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glTranslated((double)paramInt1, (double)paramInt2, (double)0.0);
        GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)f1);
        GL11.glLineWidth((float)paramFloat1);
        if (paramDouble > 0.0) {
            GL11.glBegin((int)3);
            b = 0;
            while ((double)b < paramDouble) {
                f5 = (float)((double)b * paramDouble * Math.PI / (double)paramFloat2);
                f6 = (float)(Math.cos(f5) * (double)paramFloat3);
                f7 = (float)(Math.sin(f5) * (double)paramFloat3);
                GL11.glVertex2f((float)f6, (float)f7);
                b = (byte)(b + 1);
            }
            GL11.glEnd();
        }
        if (paramDouble < 0.0) {
            GL11.glBegin((int)3);
            b = 0;
            while ((double)b > paramDouble) {
                f5 = (float)((double)b * paramDouble * Math.PI / (double)paramFloat2);
                f6 = (float)(Math.cos(f5) * (double)(-paramFloat3));
                f7 = (float)(Math.sin(f5) * (double)(-paramFloat3));
                GL11.glVertex2f((float)f6, (float)f7);
                b = (byte)(b - 1);
            }
            GL11.glEnd();
        }
        RenderMethods.disableGL2D();
        GL11.glDisable((int)3479);
        GL11.glPopMatrix();
    }

    public static void enableGL3D() {
        GL11.glDisable((int)3008);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)2884);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4353);
        GL11.glDisable((int)2896);
    }

    public static void drawBorderedRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, int paramInt1, int paramInt2) {
        RenderMethods.enableGL2D();
        RenderMethods.glColor(paramInt1);
        RenderMethods.drawRect(paramFloat1 + paramFloat5, paramFloat2 + paramFloat5, paramFloat3 - paramFloat5, paramFloat4 - paramFloat5);
        RenderMethods.glColor(paramInt2);
        RenderMethods.drawRect(paramFloat1 + paramFloat5, paramFloat2, paramFloat3 - paramFloat5, paramFloat2 + paramFloat5);
        RenderMethods.drawRect(paramFloat1, paramFloat2, paramFloat1 + paramFloat5, paramFloat4);
        RenderMethods.drawRect(paramFloat3 - paramFloat5, paramFloat2, paramFloat3, paramFloat4);
        RenderMethods.drawRect(paramFloat1 + paramFloat5, paramFloat4 - paramFloat5, paramFloat3 - paramFloat5, paramFloat4);
        RenderMethods.disableGL2D();
    }

    public static void glColor(Color paramColor) {
        GL11.glColor4f((float)((float)paramColor.getRed() / 255.0f), (float)((float)paramColor.getGreen() / 255.0f), (float)((float)paramColor.getBlue() / 255.0f), (float)((float)paramColor.getAlpha() / 255.0f));
    }

    public static void disableGL2D() {
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)2848);
        GL11.glHint((int)3154, (int)4352);
        GL11.glHint((int)3155, (int)4352);
    }

    public static void drawBox(AxisAlignedBB paramAxisAlignedBB) {
        if (paramAxisAlignedBB == null) {
            return;
        }
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glEnd();
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glEnd();
    }

    public static void drawGradientBorderedRectReliant(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, int paramInt1, int paramInt2, int paramInt3) {
        RenderMethods.enableGL2D();
        RenderMethods.drawGradientRect(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramInt3, paramInt2);
        RenderMethods.glColor(paramInt1);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glLineWidth((float)paramFloat5);
        GL11.glBegin((int)3);
        GL11.glVertex2f((float)paramFloat1, (float)paramFloat2);
        GL11.glVertex2f((float)paramFloat1, (float)paramFloat4);
        GL11.glVertex2f((float)paramFloat3, (float)paramFloat4);
        GL11.glVertex2f((float)paramFloat3, (float)paramFloat2);
        GL11.glVertex2f((float)paramFloat1, (float)paramFloat2);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        RenderMethods.disableGL2D();
    }

    public static int applyTexture(int paramInt1, int paramInt2, int paramInt3, ByteBuffer paramByteBuffer, boolean paramBoolean1, boolean paramBoolean2) {
        GL11.glBindTexture((int)3553, (int)paramInt1);
        GL11.glTexParameteri((int)3553, (int)10241, (int)(paramBoolean1 ? 9729 : 9728));
        GL11.glTexParameteri((int)3553, (int)10240, (int)(paramBoolean1 ? 9729 : 9728));
        GL11.glTexParameteri((int)3553, (int)10242, (int)(paramBoolean2 ? 10497 : 10496));
        GL11.glTexParameteri((int)3553, (int)10243, (int)(paramBoolean2 ? 10497 : 10496));
        GL11.glPixelStorei((int)3317, (int)1);
        GL11.glTexImage2D((int)3553, (int)0, (int)32856, (int)paramInt2, (int)paramInt3, (int)0, (int)6408, (int)5121, (ByteBuffer)paramByteBuffer);
        return paramInt1;
    }

    public static void disableGL3D() {
        GL11.glEnable((int)2896);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glDepthMask((boolean)true);
        GL11.glCullFace((int)1029);
    }

    public static void enableGL3D(float paramFloat) {
        GL11.glDisable((int)3008);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)2884);
        Minecraft.getMinecraft().entityRenderer.enableLightmap();
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
        GL11.glLineWidth((float)paramFloat);
    }

    public static void drawRoundedRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt1, int paramInt2) {
        RenderMethods.enableGL2D();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        RenderMethods.drawVLine(paramFloat1 *= 2.0f, (paramFloat2 *= 2.0f) + 1.0f, (paramFloat4 *= 2.0f) - 2.0f, paramInt1);
        RenderMethods.drawVLine((paramFloat3 *= 2.0f) - 1.0f, paramFloat2 + 1.0f, paramFloat4 - 2.0f, paramInt1);
        RenderMethods.drawHLine(paramFloat1 + 2.0f, paramFloat3 - 3.0f, paramFloat2, paramInt1);
        RenderMethods.drawHLine(paramFloat1 + 2.0f, paramFloat3 - 3.0f, paramFloat4 - 1.0f, paramInt1);
        RenderMethods.drawHLine(paramFloat1 + 1.0f, paramFloat1 + 1.0f, paramFloat2 + 1.0f, paramInt1);
        RenderMethods.drawHLine(paramFloat3 - 2.0f, paramFloat3 - 2.0f, paramFloat2 + 1.0f, paramInt1);
        RenderMethods.drawHLine(paramFloat3 - 2.0f, paramFloat3 - 2.0f, paramFloat4 - 2.0f, paramInt1);
        RenderMethods.drawHLine(paramFloat1 + 1.0f, paramFloat1 + 1.0f, paramFloat4 - 2.0f, paramInt1);
        RenderMethods.drawRect(paramFloat1 + 1.0f, paramFloat2 + 1.0f, paramFloat3 - 1.0f, paramFloat4 - 1.0f, paramInt2);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        RenderMethods.disableGL2D();
    }

    public static double getDiff(double paramDouble1, double paramDouble2, float paramFloat, double paramDouble3) {
        return paramDouble1 + (paramDouble2 - paramDouble1) * (double)paramFloat - paramDouble3;
    }

    public static void drawRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
        GL11.glBegin((int)7);
        GL11.glVertex2f((float)paramFloat1, (float)paramFloat4);
        GL11.glVertex2f((float)paramFloat3, (float)paramFloat4);
        GL11.glVertex2f((float)paramFloat3, (float)paramFloat2);
        GL11.glVertex2f((float)paramFloat1, (float)paramFloat2);
        GL11.glEnd();
    }

    public static void renderCrosses(AxisAlignedBB paramAxisAlignedBB) {
        GL11.glBegin((int)1);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.maxY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.minX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.minZ);
        GL11.glVertex3d((double)paramAxisAlignedBB.maxX, (double)paramAxisAlignedBB.minY, (double)paramAxisAlignedBB.maxZ);
        GL11.glEnd();
    }

    public static void glColor(int paramInt) {
        float f1 = (float)(paramInt >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(paramInt >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(paramInt >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(paramInt & 0xFF) / 255.0f;
        GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)f1);
    }

    public static void drawHLine(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt) {
        if (paramFloat2 < paramFloat1) {
            float f = paramFloat1;
            paramFloat1 = paramFloat2;
            paramFloat2 = f;
        }
        RenderMethods.drawRect(paramFloat1, paramFloat3, paramFloat2 + 1.0f, paramFloat3 + 1.0f, paramInt);
    }

    public static void drawLine(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5) {
        GL11.glDisable((int)3553);
        GL11.glLineWidth((float)paramFloat5);
        GL11.glBegin((int)1);
        GL11.glVertex2f((float)paramFloat1, (float)paramFloat2);
        GL11.glVertex2f((float)paramFloat3, (float)paramFloat4);
        GL11.glEnd();
        GL11.glEnable((int)3553);
    }

    public static void drawRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt) {
        RenderMethods.enableGL2D();
        RenderMethods.glColor(paramInt);
        RenderMethods.drawRect(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
        RenderMethods.disableGL2D();
    }

    public static void drawGradientHRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt1, int paramInt2) {
        RenderMethods.enableGL2D();
        GL11.glShadeModel((int)7425);
        GL11.glBegin((int)7);
        RenderMethods.glColor(paramInt1);
        GL11.glVertex2f((float)paramFloat1, (float)paramFloat2);
        GL11.glVertex2f((float)paramFloat1, (float)paramFloat4);
        RenderMethods.glColor(paramInt2);
        GL11.glVertex2f((float)paramFloat3, (float)paramFloat4);
        GL11.glVertex2f((float)paramFloat3, (float)paramFloat2);
        GL11.glEnd();
        GL11.glShadeModel((int)7424);
        RenderMethods.disableGL2D();
    }

    public static void rectangle(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, int paramInt) {
        double d;
        if (paramDouble1 < paramDouble3) {
            d = paramDouble1;
            paramDouble1 = paramDouble3;
            paramDouble3 = d;
        }
        if (paramDouble2 < paramDouble4) {
            d = paramDouble2;
            paramDouble2 = paramDouble4;
            paramDouble4 = d;
        }
        float f1 = (float)(paramInt >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(paramInt >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(paramInt >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(paramInt & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableLighting();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.color((float)f2, (float)f3, (float)f4, (float)f1);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        bufferBuilder.pos(paramDouble1, paramDouble4, 0.0);
        bufferBuilder.pos(paramDouble3, paramDouble4, 0.0);
        bufferBuilder.pos(paramDouble3, paramDouble2, 0.0);
        bufferBuilder.pos(paramDouble1, paramDouble2, 0.0);
        tessellator.draw();
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
    }

    public static void enableGL2D() {
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
    }

    public static void drawFullCircle(int paramInt1, int paramInt2, double paramDouble, int paramInt3) {
        paramDouble *= 2.0;
        paramInt1 *= 2;
        paramInt2 *= 2;
        float f1 = (float)(paramInt3 >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(paramInt3 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(paramInt3 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(paramInt3 & 0xFF) / 255.0f;
        RenderMethods.enableGL2D();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)f1);
        GL11.glBegin((int)6);
        for (int b = 0; b <= 360; b = (int)((byte)(b + 1))) {
            double d1 = Math.sin((double)b * Math.PI / 180.0) * paramDouble;
            double d2 = Math.cos((double)b * Math.PI / 180.0) * paramDouble;
            GL11.glVertex2d((double)((double)paramInt1 + d1), (double)((double)paramInt2 + d2));
        }
        GL11.glEnd();
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        RenderMethods.disableGL2D();
    }

    public static void drawTriangle(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        float f1 = (float)(paramInt5 >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(paramInt5 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(paramInt5 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(paramInt5 & 0xFF) / 255.0f;
        GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)f1);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)1.0f);
        GL11.glShadeModel((int)7425);
        switch (paramInt3) {
            case 0: {
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)paramInt1, (double)(paramInt2 + paramInt4));
                GL11.glVertex2d((double)(paramInt1 + paramInt4), (double)(paramInt2 - paramInt4));
                GL11.glVertex2d((double)(paramInt1 - paramInt4), (double)(paramInt2 - paramInt4));
                GL11.glEnd();
                GL11.glBegin((int)4);
                GL11.glVertex2d((double)paramInt1, (double)(paramInt2 + paramInt4));
                GL11.glVertex2d((double)(paramInt1 + paramInt4), (double)(paramInt2 - paramInt4));
                GL11.glVertex2d((double)(paramInt1 - paramInt4), (double)(paramInt2 - paramInt4));
                GL11.glEnd();
                break;
            }
            case 1: {
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)paramInt1, (double)paramInt2);
                GL11.glVertex2d((double)paramInt1, (double)(paramInt2 + paramInt4 / 2));
                GL11.glVertex2d((double)(paramInt1 + paramInt4 + paramInt4 / 2), (double)paramInt2);
                GL11.glEnd();
                GL11.glBegin((int)4);
                GL11.glVertex2d((double)paramInt1, (double)paramInt2);
                GL11.glVertex2d((double)paramInt1, (double)(paramInt2 + paramInt4 / 2));
                GL11.glVertex2d((double)(paramInt1 + paramInt4 + paramInt4 / 2), (double)paramInt2);
                GL11.glEnd();
                break;
            }
            case 3: {
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)paramInt1, (double)paramInt2);
                GL11.glVertex2d((double)((double)paramInt1 + (double)paramInt4 * 1.25), (double)(paramInt2 - paramInt4 / 2));
                GL11.glVertex2d((double)((double)paramInt1 + (double)paramInt4 * 1.25), (double)(paramInt2 + paramInt4 / 2));
                GL11.glEnd();
                GL11.glBegin((int)4);
                GL11.glVertex2d((double)((double)paramInt1 + (double)paramInt4 * 1.25), (double)(paramInt2 - paramInt4 / 2));
                GL11.glVertex2d((double)paramInt1, (double)paramInt2);
                GL11.glVertex2d((double)((double)paramInt1 + (double)paramInt4 * 1.25), (double)(paramInt2 + paramInt4 / 2));
                GL11.glEnd();
            }
        }
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
    }

    public static void drawGradientRect(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, int paramInt1, int paramInt2) {
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glShadeModel((int)7425);
        GL11.glPushMatrix();
        GL11.glBegin((int)7);
        RenderMethods.glColor(paramInt1);
        GL11.glVertex2d((double)paramDouble3, (double)paramDouble2);
        GL11.glVertex2d((double)paramDouble1, (double)paramDouble2);
        RenderMethods.glColor(paramInt2);
        GL11.glVertex2d((double)paramDouble1, (double)paramDouble4);
        GL11.glVertex2d((double)paramDouble3, (double)paramDouble4);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glShadeModel((int)7424);
    }

    public static Color rainbow(long paramLong, float paramFloat) {
        float f = (float)(System.nanoTime() + paramLong) / 1.0E10f % 1.0f;
        long l = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(f, 1.0f, 1.0f)), 16);
        Color color = new Color((int)l);
        return new Color((float)color.getRed() / 255.0f * paramFloat, (float)color.getGreen() / 255.0f * paramFloat, (float)color.getBlue() / 255.0f * paramFloat, (float)color.getAlpha() / 255.0f);
    }

    public static void drawRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8) {
        RenderMethods.enableGL2D();
        GL11.glColor4f((float)paramFloat5, (float)paramFloat6, (float)paramFloat7, (float)paramFloat8);
        RenderMethods.drawRect(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
        RenderMethods.disableGL2D();
    }

    public static void glColor(float paramFloat, int paramInt1, int paramInt2, int paramInt3) {
        float f1 = 0.003921569f * (float)paramInt1;
        float f2 = 0.003921569f * (float)paramInt2;
        float f3 = 0.003921569f * (float)paramInt3;
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)paramFloat);
    }

    public static void drawRect(Rectangle paramRectangle, int paramInt) {
        RenderMethods.drawRect(paramRectangle.x, paramRectangle.y, paramRectangle.x + paramRectangle.width, paramRectangle.y + paramRectangle.height, paramInt);
    }

    public static Color blend(Color paramColor1, Color paramColor2, float paramFloat) {
        float f = 1.0f - paramFloat;
        float[] arrayOfFloat1 = new float[3];
        float[] arrayOfFloat2 = new float[3];
        paramColor1.getColorComponents(arrayOfFloat1);
        paramColor2.getColorComponents(arrayOfFloat2);
        return new Color(arrayOfFloat1[0] * paramFloat + arrayOfFloat2[0] * f, arrayOfFloat1[1] * paramFloat + arrayOfFloat2[1] * f, arrayOfFloat1[2] * paramFloat + arrayOfFloat2[2] * f);
    }

    public static void drawHLine(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt1, int paramInt2) {
        if (paramFloat2 < paramFloat1) {
            float f = paramFloat1;
            paramFloat1 = paramFloat2;
            paramFloat2 = f;
        }
        RenderMethods.drawGradientRect(paramFloat1, paramFloat3, paramFloat2 + 1.0f, paramFloat3 + 1.0f, paramInt1, paramInt2);
    }

    public static void drawBorderedRect(Rectangle paramRectangle, float paramFloat, int paramInt1, int paramInt2) {
        float f1 = paramRectangle.x;
        float f2 = paramRectangle.y;
        float f3 = paramRectangle.x + paramRectangle.width;
        float f4 = paramRectangle.y + paramRectangle.height;
        RenderMethods.enableGL2D();
        RenderMethods.glColor(paramInt1);
        RenderMethods.drawRect(f1 + paramFloat, f2 + paramFloat, f3 - paramFloat, f4 - paramFloat);
        RenderMethods.glColor(paramInt2);
        RenderMethods.drawRect(f1 + 1.0f, f2, f3 - 1.0f, f2 + paramFloat);
        RenderMethods.drawRect(f1, f2, f1 + paramFloat, f4);
        RenderMethods.drawRect(f3 - paramFloat, f2, f3, f4);
        RenderMethods.drawRect(f1 + 1.0f, f4 - paramFloat, f3 - 1.0f, f4);
        RenderMethods.disableGL2D();
    }

    public static void drawGradientBorderedRect(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, float paramFloat, int paramInt1, int paramInt2, int paramInt3) {
        RenderMethods.enableGL2D();
        GL11.glPushMatrix();
        RenderMethods.glColor(paramInt1);
        GL11.glLineWidth((float)1.0f);
        GL11.glBegin((int)1);
        GL11.glVertex2d((double)paramDouble1, (double)paramDouble2);
        GL11.glVertex2d((double)paramDouble1, (double)paramDouble4);
        GL11.glVertex2d((double)paramDouble3, (double)paramDouble4);
        GL11.glVertex2d((double)paramDouble3, (double)paramDouble2);
        GL11.glVertex2d((double)paramDouble1, (double)paramDouble2);
        GL11.glVertex2d((double)paramDouble3, (double)paramDouble2);
        GL11.glVertex2d((double)paramDouble1, (double)paramDouble4);
        GL11.glVertex2d((double)paramDouble3, (double)paramDouble4);
        GL11.glEnd();
        GL11.glPopMatrix();
        RenderMethods.drawGradientRect(paramDouble1, paramDouble2, paramDouble3, paramDouble4, paramInt2, paramInt3);
        RenderMethods.disableGL2D();
    }

    public static void drawCircle(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt1, int paramInt2) {
        paramFloat1 *= 2.0f;
        paramFloat2 *= 2.0f;
        float f1 = (float)(paramInt2 >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(paramInt2 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(paramInt2 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(paramInt2 & 0xFF) / 255.0f;
        float f5 = (float)(6.2831852 / (double)paramInt1);
        float f6 = (float)Math.cos(f5);
        float f7 = (float)Math.sin(f5);
        float f8 = paramFloat3 *= 2.0f;
        float f9 = 0.0f;
        RenderMethods.enableGL2D();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)f1);
        GL11.glBegin((int)2);
        for (int b = 0; b < paramInt1; b = (int)((byte)(b + 1))) {
            GL11.glVertex2f((float)(f8 + paramFloat1), (float)(f9 + paramFloat2));
            float f = f8;
            f8 = f6 * f8 - f7 * f9;
            f9 = f7 * f + f6 * f9;
        }
        GL11.glEnd();
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        RenderMethods.disableGL2D();
    }

    public static void drawVLine(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt) {
        if (paramFloat3 < paramFloat2) {
            float f = paramFloat2;
            paramFloat2 = paramFloat3;
            paramFloat3 = f;
        }
        RenderMethods.drawRect(paramFloat1, paramFloat2 + 1.0f, paramFloat1 + 1.0f, paramFloat3, paramInt);
    }
}

