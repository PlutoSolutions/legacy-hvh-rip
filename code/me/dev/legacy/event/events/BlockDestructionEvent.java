/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 */
package me.dev.legacy.event.events;

import me.dev.legacy.event.EventStage;
import net.minecraft.util.math.BlockPos;

public class BlockDestructionEvent
extends EventStage {
    BlockPos nigger;

    public BlockDestructionEvent(BlockPos nigger) {
    }

    public BlockPos getBlockPos() {
        return this.nigger;
    }
}

