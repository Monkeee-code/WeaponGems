package me.monkeee.weaponGems.Events;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Crafter;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;

public class PreventItemInteractionEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_AIR &&
                event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        if (!event.hasItem()) return;
        if (!isGem(event.getItem())) return;

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK &&
                event.getClickedBlock() != null &&
                (event.getClickedBlock().getState() instanceof InventoryHolder || event.getClickedBlock().getState() instanceof CraftingInventory || event.getClickedBlock().getState() instanceof EnchantingInventory || event.getClickedBlock().getState() instanceof AnvilInventory))  {

            event.setUseItemInHand(Event.Result.DENY);
            event.setUseInteractedBlock(Event.Result.ALLOW);
            return;
        }

        event.setCancelled(true);
        event.getPlayer().sendMessage(ChatColor.RED + "[!] A gem cannot be interacted with.");
    }

    @EventHandler
    public static void onCraft(PrepareItemCraftEvent event) {
        for (ItemStack item : event.getInventory().getMatrix()) {
            if (item == null || item.getType() == Material.AIR) continue;

            if (isGem(item)) {
                event.getInventory().setResult(null);
                return;
            }
        }
    }

    @EventHandler
    public void onItemMove(InventoryMoveItemEvent event) {

        if (!(event.getDestination().getHolder() instanceof Crafter))
            return;

        ItemStack item = event.getItem();

        if (isGem(item)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (!(event.getView().getTopInventory().getHolder() instanceof Crafter))
            return;

        ItemStack item = event.getCurrentItem();
        if (isGem(item)) {
            event.setCancelled(true);
        }
    }

    private static boolean isGem(ItemStack item) {
        return NBT.get(item, nbt -> (boolean) nbt.getBoolean("isGem"));
    }
}
