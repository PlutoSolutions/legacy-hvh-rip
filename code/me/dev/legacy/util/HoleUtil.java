/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 */
package me.dev.legacy.util;

import java.util.Arrays;
import java.util.List;
import me.dev.legacy.util.CombatUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class HoleUtil {
    public static final List<BlockPos> holeBlocks = Arrays.asList(new BlockPos[]{new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(-1, 0, 0), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1)});
    private static Minecraft mc = Minecraft.getMinecraft();
    public static final Vec3d[] cityOffsets = new Vec3d[]{new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0)};

    public static boolean isInHole() {
        Vec3d playerPos = CombatUtil.interpolateEntity((Entity)HoleUtil.mc.player);
        BlockPos blockpos = new BlockPos(playerPos.x, playerPos.y, playerPos.z);
        int size = 0;
        for (BlockPos bPos : holeBlocks) {
            if (!CombatUtil.isHard(HoleUtil.mc.world.getBlockState(blockpos.add((Vec3i)bPos)).getBlock())) continue;
            ++size;
        }
        return size == 5;
    }
}

