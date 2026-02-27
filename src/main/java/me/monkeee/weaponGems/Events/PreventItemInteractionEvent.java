package me.monkeee.weaponGems.Events;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class PreventItemInteractionEvent implements Listener {

    @EventHandler
    public static void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if (!event.hasItem()) return;
        ItemStack item = event.getItem();
        if (item == null || item.getType().equals(Material.AIR)) return;
        boolean isGem = NBT.get(item, nbt -> (boolean) nbt.getBoolean("isGem"));
        if (isGem) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "A gem cannot be interacted with.");
        }
    }

    @EventHandler
    public static void onCraft(PrepareItemCraftEvent event) {
        for (ItemStack item : event.getInventory().getMatrix()) {
            if (item == null || item.getType() == Material.AIR) continue;

            boolean isGem = NBT.get(item, nbt -> (boolean) nbt.getBoolean("isGem"));
            if (isGem) {
                event.getInventory().setResult(null);
                return;
            }
        }
    }
}
