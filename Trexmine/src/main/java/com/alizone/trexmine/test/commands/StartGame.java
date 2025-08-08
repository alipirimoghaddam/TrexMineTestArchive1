package com.alizone.trexmine.test.commands;

import com.alizone.trexmine.test.Main;
import com.alizone.trexmine.test.listeners.ZombieSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartGame implements CommandExecutor {
    private final Main plugin;

    public StartGame(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.PREFIX + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        ZombieSpawner spawner = plugin.getZombieSpawner();

        if (spawner.isGameRunning()) {
            player.sendMessage(Main.PREFIX + "Game is already running!");
        } else {
            spawner.startGame(player);
            player.sendMessage(Main.PREFIX + "Zombie game started! §cWave 1");
        }
        return true;
    }
}