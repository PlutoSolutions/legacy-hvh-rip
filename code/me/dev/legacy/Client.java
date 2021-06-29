/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.Util
 *  net.minecraft.util.Util$EnumOS
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.Mod$Instance
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.opengl.Display
 */
package me.dev.legacy;

import java.io.InputStream;
import java.nio.ByteBuffer;
import me.dev.legacy.event.events.Render3DEvent;
import me.dev.legacy.features.gui.font.CustomFont;
import me.dev.legacy.manager.ColorManager;
import me.dev.legacy.manager.CommandManager;
import me.dev.legacy.manager.ConfigManager;
import me.dev.legacy.manager.EventManager;
import me.dev.legacy.manager.FileManager;
import me.dev.legacy.manager.FriendManager;
import me.dev.legacy.manager.HoleManager;
import me.dev.legacy.manager.InventoryManager;
import me.dev.legacy.manager.ModuleManager;
import me.dev.legacy.manager.PacketManager;
import me.dev.legacy.manager.PositionManager;
import me.dev.legacy.manager.PotionManager;
import me.dev.legacy.manager.ReloadManager;
import me.dev.legacy.manager.RotationManager;
import me.dev.legacy.manager.ServerManager;
import me.dev.legacy.manager.SpeedManager;
import me.dev.legacy.manager.TextManager;
import me.dev.legacy.manager.TimerManager;
import me.dev.legacy.util.Enemy;
import me.dev.legacy.util.IconUtil;
import me.dev.legacy.util.Title;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Util;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid="legacy", name="legacy", version="1.0.0")
public class Client {
    public static final String MODID = "legacy";
    public static final String MODNAME = "Legacy";
    public static final String MODVER = "1.0.0";
    public static final Logger LOGGER = LogManager.getLogger((String)"legacy");
    public static TimerManager timerManager;
    public static CommandManager commandManager;
    public static FriendManager friendManager;
    public static ModuleManager moduleManager;
    public static PacketManager packetManager;
    public static ColorManager colorManager;
    public static HoleManager holeManager;
    public static InventoryManager inventoryManager;
    public static PotionManager potionManager;
    public static RotationManager rotationManager;
    public static PositionManager positionManager;
    public static SpeedManager speedManager;
    public static ReloadManager reloadManager;
    public static FileManager fileManager;
    public static ConfigManager configManager;
    public static ServerManager serverManager;
    public static EventManager eventManager;
    public static TextManager textManager;
    public static CustomFont fontRenderer;
    public static Render3DEvent render3DEvent;
    public static Enemy enemy;
    @Mod.Instance
    public static Client INSTANCE;
    private static boolean unloaded;

    public static void load() {
        LOGGER.info("loading legacy");
        unloaded = false;
        if (reloadManager != null) {
            reloadManager.unload();
            reloadManager = null;
        }
        textManager = new TextManager();
        commandManager = new CommandManager();
        friendManager = new FriendManager();
        moduleManager = new ModuleManager();
        rotationManager = new RotationManager();
        packetManager = new PacketManager();
        eventManager = new EventManager();
        speedManager = new SpeedManager();
        potionManager = new PotionManager();
        inventoryManager = new InventoryManager();
        serverManager = new ServerManager();
        fileManager = new FileManager();
        colorManager = new ColorManager();
        positionManager = new PositionManager();
        configManager = new ConfigManager();
        holeManager = new HoleManager();
        LOGGER.info("Managers loaded.");
        moduleManager.init();
        LOGGER.info("Modules loaded.");
        configManager.init();
        eventManager.init();
        LOGGER.info("EventManager loaded.");
        textManager.init(true);
        moduleManager.onLoad();
        LOGGER.info("legacy successfully loaded!\n");
    }

    public static void unload(boolean unload) {
        LOGGER.info("unloading legacy");
        if (unload) {
            reloadManager = new ReloadManager();
            reloadManager.init(commandManager != null ? commandManager.getPrefix() : ".");
        }
        Client.onUnload();
        eventManager = null;
        friendManager = null;
        speedManager = null;
        holeManager = null;
        positionManager = null;
        rotationManager = null;
        configManager = null;
        commandManager = null;
        colorManager = null;
        serverManager = null;
        fileManager = null;
        potionManager = null;
        inventoryManager = null;
        moduleManager = null;
        textManager = null;
        LOGGER.info("legacy unloaded!\n");
    }

    public static void reload() {
        Client.unload(false);
        Client.load();
    }

    public static void onUnload() {
        if (!unloaded) {
            eventManager.onUnload();
            moduleManager.onUnload();
            configManager.saveConfig(Client.configManager.config.replaceFirst("legacy/", ""));
            moduleManager.onUnloadPost();
            unloaded = true;
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("blackbro4 n' rianix cool ppl's");
    }

    public static void setWindowIcon() {
        if (Util.getOSType() != Util.EnumOS.OSX) {
            try (InputStream inputStream16x = Minecraft.class.getResourceAsStream("/assets/legacy/icons/legacy-16x.png");
                 InputStream inputStream32x = Minecraft.class.getResourceAsStream("/assets/legacy/icons/legacy-32x.png");){
                ByteBuffer[] icons = new ByteBuffer[]{IconUtil.INSTANCE.readImageToBuffer(inputStream16x), IconUtil.INSTANCE.readImageToBuffer(inputStream32x)};
                Display.setIcon((ByteBuffer[])icons);
            }
            catch (Exception e) {
                LOGGER.error("Couldn't set Windows Icon", (Throwable)e);
            }
        }
    }

    private void setWindowsIcon() {
        Client.setWindowIcon();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register((Object)new Title());
        Client.load();
        this.setWindowsIcon();
    }

    static {
        unloaded = false;
    }
}

