/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.gameevent.TickEvent
 */
package me.dev.legacy.features.modules.movement;

import com.google.common.eventbus.Subscribe;
import me.dev.legacy.features.modules.Module;
import me.dev.legacy.features.setting.Setting;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class NoFall
extends Module {
    private final Setting<Integer> fallDist = this.register(new Setting<Integer>("FallDist", Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(40), v -> this.page.getValue() == Page.Old));
    private final Setting<Integer> fallDist2 = this.register(new Setting<Integer>("FallDist 2", Integer.valueOf(15), Integer.valueOf(10), Integer.valueOf(40), v -> this.page.getValue() == Page.Predict));
    private final Setting<Page> page = this.register(new Setting<Page>("Page", Page.Predict));

    public NoFall() {
        super("NoFall", "NoFall.", Module.Category.MOVEMENT, true, false, false);
    }

    @Subscribe
    public void onTick(TickEvent event) {
        if (NoFall.mc.world == null) {
            return;
        }
        if (this.page.getValue() == Page.Predict) {
            BlockPos[] posList;
            Vec3d vec = new Vec3d(NoFall.mc.player.lastTickPosX + (NoFall.mc.player.posX - NoFall.mc.player.lastTickPosX) * (double)mc.getRenderPartialTicks(), NoFall.mc.player.lastTickPosY + (NoFall.mc.player.posY - NoFall.mc.player.lastTickPosY) * (double)mc.getRenderPartialTicks(), NoFall.mc.player.lastTickPosZ + (NoFall.mc.player.posZ - NoFall.mc.player.lastTickPosZ) * (double)mc.getRenderPartialTicks());
            BlockPos pos = new BlockPos(vec.x, vec.y - 2.0, vec.z);
            for (BlockPos blockPos : posList = new BlockPos[]{pos.north(), pos.south(), pos.east(), pos.west(), pos.down(), pos.down()}) {
                Block block = NoFall.mc.world.getBlockState(blockPos).getBlock();
                if (NoFall.mc.player.dimension == 1) {
                    if (NoFall.mc.player.fallDistance > (float)this.fallDist2.getValue().intValue()) {
                        NoFall.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(0.0, 64.0, 0.0, false));
                        NoFall.mc.player.fallDistance = this.fallDist.getValue() + 1;
                    }
                    if (!(NoFall.mc.player.fallDistance > (float)this.fallDist.getValue().intValue()) || block == Blocks.AIR) continue;
                    NoFall.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(0.0, 64.0, 0.0, false));
                    NoFall.mc.player.fallDistance = 0.0f;
                    continue;
                }
                if (NoFall.mc.player.fallDistance > (float)this.fallDist2.getValue().intValue()) {
                    NoFall.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(NoFall.mc.player.posX, 0.0, NoFall.mc.player.posZ, false));
                    NoFall.mc.player.fallDistance = this.fallDist.getValue() + 1;
                }
                if (!(NoFall.mc.player.fallDistance > (float)this.fallDist.getValue().intValue()) || block == Blocks.AIR) continue;
                NoFall.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(NoFall.mc.player.posX, 0.0, NoFall.mc.player.posZ, false));
                NoFall.mc.player.fallDistance = 0.0f;
            }
        }
        if (this.page.getValue() == Page.Old && NoFall.mc.player.fallDistance > (float)this.fallDist.getValue().intValue()) {
            NoFall.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(NoFall.mc.player.posX, 0.0, NoFall.mc.player.posZ, false));
            NoFall.mc.player.fallDistance = 0.0f;
        }
    }

    public static enum Page {
        Predict,
        Old;

    }
}

