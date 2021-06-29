/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.client.Minecraft
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.util.CombatRules
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package me.dev.legacy.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import me.dev.legacy.Client;
import me.dev.legacy.util.HoleUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class CombatUtil {
    public static final List<Block> blackList = Arrays.asList(new Block[]{Blocks.TALLGRASS, Blocks.ENDER_CHEST, Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR});
    public static final List<Block> shulkerList = Arrays.asList(new Block[]{Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX});
    private static Minecraft mc = Minecraft.getMinecraft();
    public static final Vec3d[] cityOffsets = new Vec3d[]{new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 2.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -2.0)};
    private static final List<Integer> invalidSlots = Arrays.asList(0, 5, 6, 7, 8);

    public static int findCrapple() {
        if (CombatUtil.mc.player == null) {
            return -1;
        }
        for (int x = 0; x < CombatUtil.mc.player.inventoryContainer.getInventory().size(); ++x) {
            ItemStack stack;
            if (invalidSlots.contains(x) || (stack = (ItemStack)CombatUtil.mc.player.inventoryContainer.getInventory().get(x)).isEmpty() || !stack.getItem().equals((Object)Items.GOLDEN_APPLE) || stack.getItemDamage() == 1) continue;
            return x;
        }
        return -1;
    }

    public static int findItemSlotDamage1(Item i) {
        if (CombatUtil.mc.player == null) {
            return -1;
        }
        for (int x = 0; x < CombatUtil.mc.player.inventoryContainer.getInventory().size(); ++x) {
            ItemStack stack;
            if (invalidSlots.contains(x) || (stack = (ItemStack)CombatUtil.mc.player.inventoryContainer.getInventory().get(x)).isEmpty() || !stack.getItem().equals((Object)i) || stack.getItemDamage() != 1) continue;
            return x;
        }
        return -1;
    }

    public static int findItemSlot(Item i) {
        if (CombatUtil.mc.player == null) {
            return -1;
        }
        for (int x = 0; x < CombatUtil.mc.player.inventoryContainer.getInventory().size(); ++x) {
            ItemStack stack;
            if (invalidSlots.contains(x) || (stack = (ItemStack)CombatUtil.mc.player.inventoryContainer.getInventory().get(x)).isEmpty() || !stack.getItem().equals((Object)i)) continue;
            return x;
        }
        return -1;
    }

    public static boolean isHoldingCrystal(boolean onlyMainHand) {
        if (onlyMainHand) {
            return CombatUtil.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL;
        }
        return CombatUtil.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || CombatUtil.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
    }

    public static boolean requiredDangerSwitch(double dangerRange) {
        int dangerousCrystals = (int)CombatUtil.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).filter(entity -> (double)CombatUtil.mc.player.getDistance(entity) <= dangerRange).filter(entity -> CombatUtil.calculateDamage(entity.posX, entity.posY, entity.posZ, (Entity)CombatUtil.mc.player) >= CombatUtil.mc.player.getHealth() + CombatUtil.mc.player.getAbsorptionAmount()).count();
        return dangerousCrystals > 0;
    }

    public static boolean passesOffhandCheck(double requiredHealth, Item item, boolean isCrapple) {
        double totalPlayerHealth = CombatUtil.mc.player.getHealth() + CombatUtil.mc.player.getAbsorptionAmount();
        if (!isCrapple ? CombatUtil.findItemSlot(item) == -1 : CombatUtil.findCrapple() == -1) {
            return false;
        }
        return !(totalPlayerHealth < requiredHealth);
    }

    public static void switchOffhandStrict(int targetSlot, int step) {
        switch (step) {
            case 0: {
                CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, targetSlot, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
                break;
            }
            case 1: {
                CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
                break;
            }
            case 2: {
                CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, targetSlot, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
                CombatUtil.mc.playerController.updateController();
            }
        }
    }

    public static void switchOffhandTotemNotStrict() {
        int targetSlot = CombatUtil.findItemSlot(Items.TOTEM_OF_UNDYING);
        if (targetSlot != -1) {
            CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, targetSlot, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
            CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
            CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, targetSlot, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
            CombatUtil.mc.playerController.updateController();
        }
    }

    public static void switchOffhandNonStrict(int targetSlot) {
        CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, targetSlot, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
        CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
        CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, targetSlot, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
        CombatUtil.mc.playerController.updateController();
    }

    public static boolean canSeeBlock(BlockPos pos) {
        return CombatUtil.mc.world.rayTraceBlocks(new Vec3d(CombatUtil.mc.player.posX, CombatUtil.mc.player.posY + (double)CombatUtil.mc.player.getEyeHeight(), CombatUtil.mc.player.posZ), new Vec3d((double)pos.getX(), (double)((float)pos.getY() + 1.0f), (double)pos.getZ()), false, true, false) == null;
    }

    public static boolean placeBlock(BlockPos blockPos, boolean offhand, boolean rotate, boolean packetRotate, boolean doSwitch, boolean silentSwitch, int toSwitch) {
        if (!CombatUtil.checkCanPlace(blockPos)) {
            return false;
        }
        EnumFacing placeSide = CombatUtil.getPlaceSide(blockPos);
        BlockPos adjacentBlock = blockPos.offset(placeSide);
        EnumFacing opposingSide = placeSide.getOpposite();
        if (!CombatUtil.mc.world.getBlockState(adjacentBlock).getBlock().canCollideCheck(CombatUtil.mc.world.getBlockState(adjacentBlock), false)) {
            return false;
        }
        if (doSwitch) {
            if (silentSwitch) {
                CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(toSwitch));
            } else if (CombatUtil.mc.player.inventory.currentItem != toSwitch) {
                CombatUtil.mc.player.inventory.currentItem = toSwitch;
            }
        }
        boolean isSneak = false;
        if (blackList.contains((Object)CombatUtil.mc.world.getBlockState(adjacentBlock).getBlock()) || shulkerList.contains((Object)CombatUtil.mc.world.getBlockState(adjacentBlock).getBlock())) {
            CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)CombatUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            isSneak = true;
        }
        Vec3d hitVector = CombatUtil.getHitVector(adjacentBlock, opposingSide);
        if (rotate) {
            float[] angle = CombatUtil.getLegitRotations(hitVector);
            CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(angle[0], angle[1], CombatUtil.mc.player.onGround));
        }
        EnumHand actionHand = offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;
        CombatUtil.mc.playerController.processRightClickBlock(CombatUtil.mc.player, CombatUtil.mc.world, adjacentBlock, opposingSide, hitVector, actionHand);
        CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation(actionHand));
        if (isSneak) {
            CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)CombatUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        return true;
    }

    private static Vec3d getHitVector(BlockPos pos, EnumFacing opposingSide) {
        return new Vec3d((Vec3i)pos).add(0.5, 0.5, 0.5).add(new Vec3d(opposingSide.getDirectionVec()).scale(0.5));
    }

    public static Vec3d getHitAddition(double x, double y, double z, BlockPos pos, EnumFacing opposingSide) {
        return new Vec3d((Vec3i)pos).add(0.5, 0.5, 0.5).add(new Vec3d(opposingSide.getDirectionVec()).scale(0.5));
    }

    public static void betterRotate(BlockPos blockPos, EnumFacing opposite, boolean packetRotate) {
        float offsetZ = 0.0f;
        float offsetY = 0.0f;
        float offsetX = 0.0f;
        switch (CombatUtil.getPlaceSide(blockPos)) {
            case UP: {
                offsetZ = 0.5f;
                offsetX = 0.5f;
                offsetY = 0.0f;
                break;
            }
            case DOWN: {
                offsetZ = 0.5f;
                offsetX = 0.5f;
                offsetY = -0.5f;
                break;
            }
            case NORTH: {
                offsetX = 0.5f;
                offsetY = -0.5f;
                offsetZ = -0.5f;
                break;
            }
            case EAST: {
                offsetX = 0.5f;
                offsetY = -0.5f;
                offsetZ = 0.5f;
                break;
            }
            case SOUTH: {
                offsetX = 0.5f;
                offsetY = -0.5f;
                offsetZ = 0.5f;
                break;
            }
            case WEST: {
                offsetX = -0.5f;
                offsetY = -0.5f;
                offsetZ = 0.5f;
            }
        }
        float[] angle = CombatUtil.getLegitRotations(CombatUtil.getHitAddition(offsetX, offsetY, offsetZ, blockPos, opposite));
        CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(angle[0], angle[1], CombatUtil.mc.player.onGround));
    }

    private static EnumFacing getPlaceSide(BlockPos blockPos) {
        EnumFacing placeableSide = null;
        for (EnumFacing side : EnumFacing.values()) {
            BlockPos adjacent = blockPos.offset(side);
            if (!CombatUtil.mc.world.getBlockState(adjacent).getBlock().canCollideCheck(CombatUtil.mc.world.getBlockState(adjacent), false) || CombatUtil.mc.world.getBlockState(adjacent).getMaterial().isReplaceable()) continue;
            placeableSide = side;
        }
        return placeableSide;
    }

    public static boolean checkCanPlace(BlockPos pos) {
        if (!(CombatUtil.mc.world.getBlockState(pos).getBlock() instanceof BlockAir) && !(CombatUtil.mc.world.getBlockState(pos).getBlock() instanceof BlockLiquid)) {
            return false;
        }
        for (Entity entity : CombatUtil.mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(pos))) {
            if (entity instanceof EntityItem || entity instanceof EntityXPOrb || entity instanceof EntityArrow) continue;
            return false;
        }
        return CombatUtil.getPlaceSide(pos) != null;
    }

    public static boolean isInCity(EntityPlayer player, double range, double placeRange, boolean checkFace, boolean topBlock, boolean checkPlace, boolean checkRange) {
        BlockPos pos = new BlockPos(player.getPositionVector());
        for (EnumFacing face : EnumFacing.values()) {
            if (face == EnumFacing.UP || face == EnumFacing.DOWN) continue;
            BlockPos pos1 = pos.offset(face);
            BlockPos pos2 = pos1.offset(face);
            if (!(CombatUtil.mc.world.getBlockState(pos1).getBlock() == Blocks.AIR && (CombatUtil.mc.world.getBlockState(pos2).getBlock() == Blocks.AIR && CombatUtil.isHard(CombatUtil.mc.world.getBlockState(pos2.up()).getBlock()) || !checkFace) && !checkRange || CombatUtil.mc.player.getDistanceSq(pos2) <= placeRange * placeRange && CombatUtil.mc.player.getDistanceSq((Entity)player) <= range * range && CombatUtil.isHard(CombatUtil.mc.world.getBlockState(pos.up(3)).getBlock())) && topBlock) continue;
            return true;
        }
        return false;
    }

    public static boolean isHard(Block block) {
        return block == Blocks.OBSIDIAN || block == Blocks.BEDROCK;
    }

    public static boolean canLegPlace(EntityPlayer player, double range) {
        int safety = 0;
        int blocksInRange = 0;
        for (Vec3d vec : HoleUtil.cityOffsets) {
            BlockPos pos = CombatUtil.getFlooredPosition((Entity)player).add(vec.x, vec.y, vec.z);
            if (CombatUtil.mc.world.getBlockState(pos).getBlock() == Blocks.OBSIDIAN || CombatUtil.mc.world.getBlockState(pos).getBlock() == Blocks.BEDROCK) {
                ++safety;
            }
            if (!(CombatUtil.mc.player.getDistanceSq(pos) >= range * range)) continue;
            ++blocksInRange;
        }
        return safety == 4 && blocksInRange >= 1;
    }

    public static int getSafetyFactor(BlockPos pos) {
        return 0;
    }

    public static boolean canPlaceCrystal(BlockPos pos, double range, double wallsRange, boolean raytraceCheck) {
        BlockPos up = pos.up();
        BlockPos up1 = up.up();
        AxisAlignedBB bb = new AxisAlignedBB(up).expand(0.0, 1.0, 0.0);
        return (CombatUtil.mc.world.getBlockState(pos).getBlock() == Blocks.OBSIDIAN || CombatUtil.mc.world.getBlockState(pos).getBlock() == Blocks.BEDROCK) && CombatUtil.mc.world.getBlockState(up).getBlock() == Blocks.AIR && CombatUtil.mc.world.getBlockState(up1).getBlock() == Blocks.AIR && CombatUtil.mc.world.getEntitiesWithinAABB(Entity.class, bb).isEmpty() && CombatUtil.mc.player.getDistanceSq(pos) <= range * range && !raytraceCheck || CombatUtil.rayTraceRangeCheck(pos, wallsRange, 0.0);
    }

    public static int getVulnerability(EntityPlayer player, double range, double placeRange, double wallsRange, double maxSelfDamage, double maxFriendDamage, double minDamage, double friendRange, double facePlaceHP, int minArmor, boolean cityCheck, boolean rayTrace, boolean lowArmorCheck, boolean antiSuicide, boolean antiFriendPop) {
        if (CombatUtil.isInCity(player, range, placeRange, true, true, true, false) && cityCheck) {
            return 5;
        }
        if (CombatUtil.getClosestValidPos(player, maxSelfDamage, maxFriendDamage, minDamage, placeRange, wallsRange, friendRange, rayTrace, antiSuicide, antiFriendPop, true) != null) {
            return 4;
        }
        if ((double)(player.getHealth() + player.getAbsorptionAmount()) <= facePlaceHP) {
            return 3;
        }
        if (CombatUtil.isArmorLow(player, minArmor, true) && lowArmorCheck) {
            return 2;
        }
        return 0;
    }

    public static Map<BlockPos, Double> mapBlockDamage(EntityPlayer player, double maxSelfDamage, double maxFriendDamage, double minDamage, double placeRange, double wallsRange, double friendRange, boolean rayTrace, boolean antiSuicide, boolean antiFriendPop) {
        HashMap<BlockPos, Double> damageMap = new HashMap<BlockPos, Double>();
        for (BlockPos pos : CombatUtil.getSphere(new BlockPos((Vec3i)CombatUtil.getFlooredPosition((Entity)CombatUtil.mc.player)), (float)placeRange, (int)placeRange, false, true, 0)) {
            double damage;
            if (!CombatUtil.canPlaceCrystal(pos, placeRange, wallsRange, rayTrace) || !CombatUtil.checkFriends(pos, maxFriendDamage, friendRange, antiFriendPop) || !CombatUtil.checkSelf(pos, maxSelfDamage, antiSuicide) || rayTrace && !CombatUtil.rayTraceRangeCheck(pos, wallsRange, 0.0) || (damage = (double)CombatUtil.calculateDamage(pos, (Entity)player)) < minDamage) continue;
            damageMap.put(pos, damage);
        }
        return damageMap;
    }

    public static boolean checkFriends(BlockPos pos, double maxFriendDamage, double friendRange, boolean antiFriendPop) {
        for (EntityPlayer player : CombatUtil.mc.world.playerEntities) {
            if (CombatUtil.mc.player.getDistanceSq((Entity)player) > friendRange * friendRange) continue;
            if ((double)CombatUtil.calculateDamage(pos, (Entity)player) > maxFriendDamage) {
                return false;
            }
            if (!(CombatUtil.calculateDamage(pos, (Entity)player) > player.getHealth() + player.getAbsorptionAmount()) || !antiFriendPop) continue;
            return false;
        }
        return true;
    }

    public static boolean checkFriends(EntityEnderCrystal crystal, double maxFriendDamage, double friendRange, boolean antiFriendPop) {
        for (EntityPlayer player : CombatUtil.mc.world.playerEntities) {
            if (CombatUtil.mc.player.getDistanceSq((Entity)player) > friendRange * friendRange) continue;
            if ((double)CombatUtil.calculateDamage((Entity)crystal, (Entity)player) > maxFriendDamage) {
                return false;
            }
            if (!(CombatUtil.calculateDamage((Entity)crystal, (Entity)player) > player.getHealth() + player.getAbsorptionAmount()) || !antiFriendPop) continue;
            return false;
        }
        return true;
    }

    public static boolean checkSelf(BlockPos pos, double maxSelfDamage, boolean antiSuicide) {
        boolean willPopSelf = CombatUtil.calculateDamage(pos, (Entity)CombatUtil.mc.player) > CombatUtil.mc.player.getHealth() + CombatUtil.mc.player.getAbsorptionAmount();
        boolean willDamageSelf = (double)CombatUtil.calculateDamage(pos, (Entity)CombatUtil.mc.player) > maxSelfDamage;
        return (!antiSuicide || !willPopSelf) && !willDamageSelf;
    }

    public static boolean checkSelf(EntityEnderCrystal crystal, double maxSelfDamage, boolean antiSuicide) {
        boolean willPopSelf = CombatUtil.calculateDamage((Entity)crystal, (Entity)CombatUtil.mc.player) > CombatUtil.mc.player.getHealth() + CombatUtil.mc.player.getAbsorptionAmount();
        boolean willDamageSelf = (double)CombatUtil.calculateDamage((Entity)crystal, (Entity)CombatUtil.mc.player) > maxSelfDamage;
        return (!antiSuicide || !willPopSelf) && !willDamageSelf;
    }

    public static boolean isPosValid(EntityPlayer player, BlockPos pos, double maxSelfDamage, double maxFriendDamage, double minDamage, double placeRange, double wallsRange, double friendRange, boolean rayTrace, boolean antiSuicide, boolean antiFriendPop) {
        if (pos == null) {
            return false;
        }
        if (!CombatUtil.isHard(CombatUtil.mc.world.getBlockState(pos).getBlock())) {
            return false;
        }
        if (!CombatUtil.canPlaceCrystal(pos, placeRange, wallsRange, rayTrace)) {
            return false;
        }
        if (!CombatUtil.checkFriends(pos, maxFriendDamage, friendRange, antiFriendPop)) {
            return false;
        }
        if (!CombatUtil.checkSelf(pos, maxSelfDamage, antiSuicide)) {
            return false;
        }
        double damage = CombatUtil.calculateDamage(pos, (Entity)player);
        if (damage < minDamage) {
            return false;
        }
        return !rayTrace || CombatUtil.rayTraceRangeCheck(pos, wallsRange, 0.0);
    }

    public static BlockPos getClosestValidPos(EntityPlayer player, double maxSelfDamage, double maxFriendDamage, double minDamage, double placeRange, double wallsRange, double friendRange, boolean rayTrace, boolean antiSuicide, boolean antiFriendPop, boolean multiplace) {
        double highestDamage = -1.0;
        BlockPos finalPos = null;
        if (player == null) {
            return null;
        }
        List<BlockPos> placeLocations = CombatUtil.getSphere(new BlockPos((Vec3i)CombatUtil.getFlooredPosition((Entity)CombatUtil.mc.player)), (float)placeRange, (int)placeRange, false, true, 0);
        placeLocations.sort(Comparator.comparing(blockPos -> CombatUtil.mc.player.getDistanceSq(blockPos)));
        for (BlockPos pos : placeLocations) {
            double damage;
            if (!CombatUtil.canPlaceCrystal(pos, placeRange, wallsRange, rayTrace) || rayTrace && !CombatUtil.rayTraceRangeCheck(pos, wallsRange, 0.0) || (damage = (double)CombatUtil.calculateDamage(pos, (Entity)player)) < minDamage || !CombatUtil.checkFriends(pos, maxFriendDamage, friendRange, antiFriendPop) || !CombatUtil.checkSelf(pos, maxSelfDamage, antiSuicide)) continue;
            if (damage > 15.0) {
                return pos;
            }
            if (!(damage > highestDamage)) continue;
            highestDamage = damage;
            finalPos = pos;
        }
        return finalPos;
    }

    public static BlockPos getClosestValidPosMultiThread(EntityPlayer player, double maxSelfDamage, double maxFriendDamage, double minDamage, double placeRange, double wallsRange, double friendRange, boolean rayTrace, boolean antiSuicide, boolean antiFriendPop) {
        CopyOnWriteArrayList<ValidPosThread> threads = new CopyOnWriteArrayList<ValidPosThread>();
        BlockPos finalPos = null;
        for (BlockPos pos : CombatUtil.getSphere(new BlockPos(player.getPositionVector()), 13.0f, 13, false, true, 0)) {
            ValidPosThread thread2 = new ValidPosThread(player, pos, maxSelfDamage, maxFriendDamage, minDamage, placeRange, wallsRange, friendRange, rayTrace, antiSuicide, antiFriendPop);
            threads.add(thread2);
            thread2.start();
        }
        boolean areAllInvalid = false;
        do {
            for (ValidPosThread thread2 : threads) {
                if (!thread2.isInterrupted() || !thread2.isValid) continue;
                finalPos = thread2.pos;
            }
            areAllInvalid = threads.stream().noneMatch(thread -> thread.isValid && thread.isInterrupted());
        } while (finalPos == null && !areAllInvalid);
        Client.LOGGER.info(finalPos == null ? "pos was null" : finalPos.toString());
        return finalPos;
    }

    public static List<BlockPos> getSphere(BlockPos pos, float r, int h, boolean hollow, boolean sphere, int plus_y) {
        ArrayList<BlockPos> circleblocks = new ArrayList<BlockPos>();
        int cx = pos.getX();
        int cy = pos.getY();
        int cz = pos.getZ();
        int x = cx - (int)r;
        while ((float)x <= (float)cx + r) {
            int z = cz - (int)r;
            while ((float)z <= (float)cz + r) {
                int y = sphere ? cy - (int)r : cy;
                while (true) {
                    float f = y;
                    float f2 = sphere ? (float)cy + r : (float)(cy + h);
                    if (!(f < f2)) break;
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? (cy - y) * (cy - y) : 0);
                    if (!(!(dist < (double)(r * r)) || hollow && dist < (double)((r - 1.0f) * (r - 1.0f)))) {
                        BlockPos l = new BlockPos(x, y + plus_y, z);
                        circleblocks.add(l);
                    }
                    ++y;
                }
                ++z;
            }
            ++x;
        }
        return circleblocks;
    }

    public static boolean isArmorLow(EntityPlayer player, int durability, boolean checkDurability) {
        for (ItemStack piece : player.inventory.armorInventory) {
            if (piece == null) {
                return true;
            }
            if (!checkDurability || CombatUtil.getItemDamage(piece) >= durability) continue;
            return true;
        }
        return false;
    }

    public static int getItemDamage(ItemStack stack) {
        return stack.getMaxDamage() - stack.getItemDamage();
    }

    public static boolean rayTraceRangeCheck(Entity target, double range) {
        boolean isVisible = CombatUtil.mc.player.canEntityBeSeen(target);
        return !isVisible || CombatUtil.mc.player.getDistanceSq(target) <= range * range;
    }

    public static boolean rayTraceRangeCheck(BlockPos pos, double range, double height) {
        RayTraceResult result = CombatUtil.mc.world.rayTraceBlocks(new Vec3d(CombatUtil.mc.player.posX, CombatUtil.mc.player.posY + (double)CombatUtil.mc.player.getEyeHeight(), CombatUtil.mc.player.posZ), new Vec3d((double)pos.getX(), (double)pos.getY() + height, (double)pos.getZ()), false, true, false);
        return result == null || CombatUtil.mc.player.getDistanceSq(pos) <= range * range;
    }

    public static EntityEnderCrystal getClosestValidCrystal(EntityPlayer player, double maxSelfDamage, double maxFriendDamage, double minDamage, double breakRange, double wallsRange, double friendRange, boolean rayTrace, boolean antiSuicide, boolean antiFriendPop) {
        if (player == null) {
            return null;
        }
        List crystals = CombatUtil.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).filter(entity -> CombatUtil.mc.player.getDistanceSq(entity) <= breakRange * breakRange).sorted(Comparator.comparingDouble(entity -> CombatUtil.mc.player.getDistanceSq(entity))).map(entity -> (EntityEnderCrystal)entity).collect(Collectors.toList());
        for (EntityEnderCrystal crystal : crystals) {
            if (rayTrace && !CombatUtil.rayTraceRangeCheck((Entity)crystal, wallsRange) || (double)CombatUtil.calculateDamage((Entity)crystal, (Entity)player) < minDamage || !CombatUtil.checkSelf(crystal, maxSelfDamage, antiSuicide) || !CombatUtil.checkFriends(crystal, maxFriendDamage, friendRange, antiFriendPop)) continue;
            return crystal;
        }
        return null;
    }

    public static List<BlockPos> getDisc(BlockPos pos, float r) {
        ArrayList<BlockPos> circleblocks = new ArrayList<BlockPos>();
        int cx = pos.getX();
        int cy = pos.getY();
        int cz = pos.getZ();
        int x = cx - (int)r;
        while ((float)x <= (float)cx + r) {
            int z = cz - (int)r;
            while ((float)z <= (float)cz + r) {
                double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z);
                if (dist < (double)(r * r)) {
                    BlockPos position = new BlockPos(x, cy, z);
                    circleblocks.add(position);
                }
                ++z;
            }
            ++x;
        }
        return circleblocks;
    }

    public static BlockPos getFlooredPosition(Entity entity) {
        return new BlockPos(Math.floor(entity.posX), Math.floor(entity.posY), Math.floor(entity.posZ));
    }

    public static float calculateDamage(double posX, double posY, double posZ, Entity entity) {
        float doubleExplosionSize = 12.0f;
        double distancedsize = entity.getDistance(posX, posY, posZ) / (double)doubleExplosionSize;
        Vec3d vec3d = new Vec3d(posX, posY, posZ);
        double blockDensity = 0.0;
        try {
            blockDensity = entity.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
        }
        catch (Exception exception) {
            // empty catch block
        }
        double v = (1.0 - distancedsize) * blockDensity;
        float damage = (int)((v * v + v) / 2.0 * 7.0 * (double)doubleExplosionSize + 1.0);
        double finald = 1.0;
        if (entity instanceof EntityLivingBase) {
            finald = CombatUtil.getBlastReduction((EntityLivingBase)entity, CombatUtil.getDamageMultiplied(damage), new Explosion((World)Minecraft.getMinecraft().world, null, posX, posY, posZ, 6.0f, false, true));
        }
        return (float)finald;
    }

    public static float getBlastReduction(EntityLivingBase entity, float damageI, Explosion explosion) {
        float damage = damageI;
        if (entity instanceof EntityPlayer) {
            EntityPlayer ep = (EntityPlayer)entity;
            DamageSource ds = DamageSource.causeExplosionDamage((Explosion)explosion);
            damage = CombatRules.getDamageAfterAbsorb((float)damage, (float)ep.getTotalArmorValue(), (float)((float)ep.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue()));
            int k = 0;
            try {
                k = EnchantmentHelper.getEnchantmentModifierDamage((Iterable)ep.getArmorInventoryList(), (DamageSource)ds);
            }
            catch (Exception exception) {
                // empty catch block
            }
            float f = MathHelper.clamp((float)k, (float)0.0f, (float)20.0f);
            damage *= 1.0f - f / 25.0f;
            if (entity.isPotionActive(MobEffects.RESISTANCE)) {
                damage -= damage / 4.0f;
            }
            damage = Math.max(damage, 0.0f);
            return damage;
        }
        damage = CombatRules.getDamageAfterAbsorb((float)damage, (float)entity.getTotalArmorValue(), (float)((float)entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue()));
        return damage;
    }

    public static float getDamageMultiplied(float damage) {
        int diff = Minecraft.getMinecraft().world.getDifficulty().getId();
        return damage * (diff == 0 ? 0.0f : (diff == 2 ? 1.0f : (diff == 1 ? 0.5f : 1.5f)));
    }

    public static float calculateDamage(Entity crystal, Entity entity) {
        return CombatUtil.calculateDamage(crystal.posX, crystal.posY, crystal.posZ, entity);
    }

    public static float calculateDamage(BlockPos pos, Entity entity) {
        return CombatUtil.calculateDamage((double)pos.getX() + 0.5, pos.getY() + 1, (double)pos.getZ() + 0.5, entity);
    }

    public static Vec3d interpolateEntity(Entity entity) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)mc.getRenderPartialTicks(), entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)mc.getRenderPartialTicks(), entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)mc.getRenderPartialTicks());
    }

    public static float[] calcAngle(Vec3d from, Vec3d to) {
        double difX = to.x - from.x;
        double difY = (to.y - from.y) * -1.0;
        double difZ = to.z - from.z;
        double dist = MathHelper.sqrt((double)(difX * difX + difZ * difZ));
        return new float[]{(float)MathHelper.wrapDegrees((double)(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0)), (float)MathHelper.wrapDegrees((double)Math.toDegrees(Math.atan2(difY, dist)))};
    }

    public static float[] getLegitRotations(Vec3d vec) {
        Vec3d eyesPos = new Vec3d(CombatUtil.mc.player.posX, CombatUtil.mc.player.posY + (double)CombatUtil.mc.player.getEyeHeight(), CombatUtil.mc.player.posZ);
        double diffX = vec.x - eyesPos.x;
        double diffY = vec.y - eyesPos.y;
        double diffZ = vec.z - eyesPos.z;
        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        return new float[]{CombatUtil.mc.player.rotationYaw + MathHelper.wrapDegrees((float)(yaw - CombatUtil.mc.player.rotationYaw)), CombatUtil.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(pitch - CombatUtil.mc.player.rotationPitch))};
    }

    public static class CombatPosInfo {
        public EntityPlayer player;
        public BlockPos pos;
        public float damage;

        public CombatPosInfo(EntityPlayer player, BlockPos pos, float damage) {
            this.pos = pos;
            this.damage = damage;
            this.player = player;
        }
    }

    public static class ValidPosThread
    extends Thread {
        BlockPos pos;
        EntityPlayer player;
        double maxSelfDamage;
        double maxFriendDamage;
        double minDamage;
        double placeRange;
        double wallsRange;
        double friendRange;
        boolean rayTrace;
        boolean antiSuicide;
        boolean antiFriendPop;
        public float damage;
        public boolean isValid;
        public CombatPosInfo info;

        public ValidPosThread(EntityPlayer player, BlockPos pos, double maxSelfDamage, double maxFriendDamage, double minDamage, double placeRange, double wallsRange, double friendRange, boolean rayTrace, boolean antiSuicide, boolean antiFriendPop) {
            super("Break");
            this.pos = pos;
            this.maxSelfDamage = maxSelfDamage;
            this.maxFriendDamage = maxFriendDamage;
            this.minDamage = minDamage;
            this.placeRange = placeRange;
            this.wallsRange = wallsRange;
            this.friendRange = friendRange;
            this.rayTrace = rayTrace;
            this.antiSuicide = antiSuicide;
            this.antiFriendPop = antiFriendPop;
            this.player = player;
        }

        @Override
        public void run() {
            if (!(mc.player.getDistanceSq(this.pos) > this.placeRange * this.placeRange) && CombatUtil.canPlaceCrystal(this.pos, this.placeRange, this.wallsRange, this.rayTrace) && CombatUtil.checkFriends(this.pos, this.maxFriendDamage, this.friendRange, this.antiFriendPop) && CombatUtil.checkSelf(this.pos, this.maxSelfDamage, this.antiSuicide)) {
                this.damage = CombatUtil.calculateDamage(this.pos, (Entity)this.player);
                if ((double)this.damage >= this.minDamage && (!this.rayTrace || CombatUtil.rayTraceRangeCheck(this.pos, this.wallsRange, 0.0))) {
                    this.isValid = true;
                    this.info = new CombatPosInfo(this.player, this.pos, this.damage);
                    Client.LOGGER.info("Pos was valid.");
                    return;
                }
            }
            this.isValid = false;
            this.info = new CombatPosInfo(this.player, this.pos, -1.0f);
            Client.LOGGER.info("Pos was invalid.");
        }
    }
}

