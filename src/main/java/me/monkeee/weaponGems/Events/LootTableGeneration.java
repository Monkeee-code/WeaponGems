package me.monkeee.weaponGems.Events;

import me.monkeee.weaponGems.API.GemDefinition;
import me.monkeee.weaponGems.API.GemRegistry;
import me.monkeee.weaponGems.Handlers.ItemHandler;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.Lootable;

public class LootTableGeneration implements Listener {

    @EventHandler
    public static void onLootGenerate(LootGenerateEvent event) {
        if (!(event.getInventoryHolder() instanceof Lootable)) return;

        LootTable table = event.getLootTable();
        NamespacedKey key = table.getKey();

        for (GemDefinition gem : GemRegistry.getAll()) {
            if (gem.getLootTables().contains(key)) {
                tryAddGem(event, gem);
            }
        }
    }

    private static void tryAddGem(LootGenerateEvent event, GemDefinition gem) {
        if (Math.random() <= gem.getSpawnChance()) {
            ItemStack item = ItemHandler.createGem(gem.getID());
            event.getLoot().add(item);
        }
    }
}