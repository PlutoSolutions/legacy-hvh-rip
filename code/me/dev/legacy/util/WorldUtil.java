/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.World
 */
package me.dev.legacy.util;

import me.dev.legacy.MinecraftInstance;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class WorldUtil
implements MinecraftInstance {
    public static void placeBlock(BlockPos pos) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            if (WorldUtil.mc.world.getBlockState(pos.offset(enumFacing)).getBlock().equals((Object)Blocks.AIR) || WorldUtil.isIntercepted(pos)) continue;
            Vec3d vec = new Vec3d((double)pos.getX() + 0.5 + (double)enumFacing.getXOffset() * 0.5, (double)pos.getY() + 0.5 + (double)enumFacing.getYOffset() * 0.5, (double)pos.getZ() + 0.5 + (double)enumFacing.getZOffset() * 0.5);
            float[] old = new float[]{WorldUtil.mc.player.rotationYaw, WorldUtil.mc.player.rotationPitch};
            WorldUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation((float)Math.toDegrees(Math.atan2(vec.z - WorldUtil.mc.player.posZ, vec.x - WorldUtil.mc.player.posX)) - 90.0f, (float)(-Math.toDegrees(Math.atan2(vec.y - (WorldUtil.mc.player.posY + (double)WorldUtil.mc.player.getEyeHeight()), Math.sqrt((vec.x - WorldUtil.mc.player.posX) * (vec.x - WorldUtil.mc.player.posX) + (vec.z - WorldUtil.mc.player.posZ) * (vec.z - WorldUtil.mc.player.posZ))))), WorldUtil.mc.player.onGround));
            WorldUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)WorldUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            WorldUtil.mc.playerController.processRightClickBlock(WorldUtil.mc.player, WorldUtil.mc.world, pos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)pos), EnumHand.MAIN_HAND);
            WorldUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
            WorldUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)WorldUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            WorldUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(old[0], old[1], WorldUtil.mc.player.onGround));
            return;
        }
    }

    public static void placeBlock(BlockPos pos, int slot) {
        if (slot == -1) {
            return;
        }
        int prev = WorldUtil.mc.player.inventory.currentItem;
        WorldUtil.mc.player.inventory.currentItem = slot;
        WorldUtil.placeBlock(pos);
        WorldUtil.mc.player.inventory.currentItem = prev;
    }

    public static boolean isIntercepted(BlockPos pos) {
        for (Entity entity : WorldUtil.mc.world.loadedEntityList) {
            if (!new AxisAlignedBB(pos).intersects(entity.getEntityBoundingBox())) continue;
            return true;
        }
        return false;
    }

    public static BlockPos GetLocalPlayerPosFloored() {
        return new BlockPos(Math.floor(WorldUtil.mc.player.posX), Math.floor(WorldUtil.mc.player.posY), Math.floor(WorldUtil.mc.player.posZ));
    }

    public static boolean canBreak(BlockPos pos) {
        return WorldUtil.mc.world.getBlockState(pos).getBlock().getBlockHardness(WorldUtil.mc.world.getBlockState(pos), (World)WorldUtil.mc.world, pos) != -1.0f;
    }
}

