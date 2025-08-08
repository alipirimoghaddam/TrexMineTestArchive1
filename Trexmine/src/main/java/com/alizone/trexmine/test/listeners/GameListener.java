package com.alizone.trexmine.test.listeners;

import com.alizone.trexmine.test.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class GameListener implements Listener {
    private final Main plugin;

    public GameListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setGameMode(GameMode.SURVIVAL);
        giveStarterKit(player);
        player.sendMessage(Main.PREFIX + "Type §a/startzombie §7to begin!");
    }

    private void giveStarterKit(Player player) {
        PlayerInventory inv = player.getInventory();
        inv.clear();

        // Starter items
        inv.addItem(new ItemStack(org.bukkit.Material.IRON_SWORD));
        inv.addItem(new ItemStack(org.bukkit.Material.BOW));
        inv.addItem(new ItemStack(org.bukkit.Material.ARROW, 64));
        inv.addItem(new ItemStack(org.bukkit.Material.COOKED_BEEF, 16));

        // Armor
        inv.setHelmet(new ItemStack(org.bukkit.Material.IRON_HELMET));
        inv.setChestplate(new ItemStack(org.bukkit.Material.IRON_CHESTPLATE));
        inv.setLeggings(new ItemStack(org.bukkit.Material.IRON_LEGGINGS));
        inv.setBoots(new ItemStack(org.bukkit.Material.IRON_BOOTS));
    }
}