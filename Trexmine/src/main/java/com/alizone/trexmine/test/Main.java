package com.alizone.trexmine.test;

import com.alizone.trexmine.test.commands.StartGame;
import com.alizone.trexmine.test.listeners.GameListener;
import com.alizone.trexmine.test.listeners.ZombieSpawner;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static final String PREFIX = "§8[§6Trexmine Test§8] §7";
    private ZombieSpawner zombieSpawner;

    @Override
    public void onEnable() {
        // Register events
        getServer().getPluginManager().registerEvents(new GameListener(this), this);
        getServer().getPluginManager().registerEvents(zombieSpawner = new ZombieSpawner(this), this);

        // Register command
        getCommand("startzombie").setExecutor(new StartGame(this));

        getLogger().info(PREFIX + "Plugin enabled!");
    }

    @Override
    public void onDisable() {
        zombieSpawner.stopGame();
        getLogger().info(PREFIX + "Plugin disabled!");
    }

    public ZombieSpawner getZombieSpawner() {
        return zombieSpawner;
    }
}