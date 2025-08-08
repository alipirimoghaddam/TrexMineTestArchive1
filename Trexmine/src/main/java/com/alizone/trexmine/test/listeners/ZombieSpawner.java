package com.alizone.trexmine.test.listeners;

import com.alizone.trexmine.test.Main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class ZombieSpawner implements org.bukkit.event.Listener {
    private final Main plugin;
    private BukkitTask waveTask;
    private int wave = 0;
    private boolean gameRunning = false;
    private final List<Zombie> zombies = new ArrayList<>();

    public ZombieSpawner(Main plugin) {
        this.plugin = plugin;
    }

    public void startGame(Player player) {
        this.wave = 1;
        this.gameRunning = true;
        startWave(player);
    }

    public void stopGame() {
        if (waveTask != null) {
            waveTask.cancel();
        }
        zombies.forEach(zombie -> {
            if (zombie.isValid()) zombie.remove();
        });
        zombies.clear();
        gameRunning = false;
    }

    private void startWave(Player player) {
        waveTask = new BukkitRunnable() {
            @Override
            public void run() {
                spawnZombies(player, wave);

                if (wave < 10) {
                    wave++;
                    player.sendMessage(Main.PREFIX + "§eWave " + wave + " starting in 15 seconds!");
                    // Delay next wave
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            startWave(player);
                        }
                    }.runTaskLater(plugin, 20 * 15);
                } else {
                    player.sendMessage(Main.PREFIX + "§a§lGAME COMPLETE! Well done!");
                    stopGame();
                }
            }
        }.runTaskLater(plugin, 20 * 5); // 5 second initial delay
    }

    private void spawnZombies(Player player, int wave) {
        Location loc = player.getLocation();
        World world = player.getWorld();
        int zombieCount = wave * 3 + 5; // 8, 11, 14, etc.

        for (int i = 0; i < zombieCount; i++) {
            Location spawnLoc = loc.clone().add(
                    (Math.random() * 20) - 10,
                    0,
                    (Math.random() * 20) - 10
            );
            spawnLoc.setY(world.getHighestBlockYAt(spawnLoc) + 1);

            Zombie zombie = (Zombie) world.spawnEntity(spawnLoc, EntityType.ZOMBIE);
            zombie.setCustomName("§cZombie §7(Wave " + wave + ")");
            zombie.setCustomNameVisible(true);
            zombies.add(zombie);
        }
    }

    public boolean isGameRunning() {
        return gameRunning;
    }
}