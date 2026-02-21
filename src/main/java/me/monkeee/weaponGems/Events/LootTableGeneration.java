package me.monkeee.weaponGems.Events;

import me.monkeee.weaponGems.Handlers.ItemHandler;
import me.monkeee.weaponGems.Handlers.JsonHandler;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.LootGenerateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.Lootable;

import javax.naming.Name;

public class LootTableGeneration implements Listener {

    @EventHandler
    public static void onLootGenerate(LootGenerateEvent event) {
        if (!(event.getInventoryHolder() instanceof Lootable lootable)) return;

        LootTable table = event.getLootTable();

        NamespacedKey key = table.getKey();

        if (key.equals(NamespacedKey.minecraft("chests/village/village_toolsmith"))) {
            tryAddGem(event, "divan_core");
        }
        if (key.equals(NamespacedKey.minecraft("chests/end_city_treasure"))) {
            tryAddGem(event, "deflection_eye");
        }
        if (key.equals(NamespacedKey.minecraft("chests/village/village_weaponsmith"))) {
            tryAddGem(event, "darkstone");
        }
        if (key.equals(NamespacedKey.minecraft("chests/village/village_armorer"))) {
            tryAddGem(event, "jade");
        }
        if (key.equals(NamespacedKey.minecraft("chests/simple_dungeon"))) {
            tryAddGem(event, "spider_fang");
        }
        if (key.equals(NamespacedKey.minecraft("chests/ruined_portal")) || key.equals(NamespacedKey.minecraft("chests/bastion_treasure")) || key.equals(NamespacedKey.minecraft("chests/ancient_city"))) {
            tryAddGem(event, "ruby");
        }

    }

    private static void tryAddGem(LootGenerateEvent event, String gemType) {
        if (Math.random() <= JsonHandler.SpawnChanceReader(gemType, "chance")) {
            ItemStack gem = ItemHandler.createGem(gemType); // your existing method
            event.getLoot().add(gem);
        }
    }
}
