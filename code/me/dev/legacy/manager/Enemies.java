/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  io.netty.util.internal.ConcurrentSet
 */
package me.dev.legacy.manager;

import io.netty.util.internal.ConcurrentSet;
import me.dev.legacy.Client;
import me.dev.legacy.manager.RotationManager;
import me.dev.legacy.util.Enemy;

public class Enemies
extends RotationManager {
    private static ConcurrentSet<Enemy> enemies = new ConcurrentSet();

    public static void addEnemy(String name) {
        enemies.add((Object)new Enemy(name));
    }

    public static void delEnemy(String name) {
        enemies.remove((Object)Enemies.getEnemyByName(name));
    }

    public static Enemy getEnemyByName(String name) {
        for (Enemy e : enemies) {
            if (!Client.enemy.username.equalsIgnoreCase(name)) continue;
            return e;
        }
        return null;
    }

    public static ConcurrentSet<Enemy> getEnemies() {
        return enemies;
    }

    public static boolean isEnemy(String name) {
        return enemies.stream().anyMatch(enemy -> enemy.username.equalsIgnoreCase(name));
    }
}

