package me.monkeee.weaponGems.Events;

import me.monkeee.weaponGems.GemTypes;
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

        if (table == null) return;

        NamespacedKey key = table.getKey();

        if (key.equals(NamespacedKey.minecraft("chests/village/village_toolsmith"))) {
            tryAddGem(event, GemTypes.divan_core);
        }
        if (key.equals(NamespacedKey.minecraft("chests/end_city_treasure"))) {
            tryAddGem(event, GemTypes.deflection_eye);
        }
        if (key.equals(NamespacedKey.minecraft("chests/village/village_weaponsmith"))) {
            tryAddGem(event, GemTypes.darkstone);
        }
        if (key.equals(NamespacedKey.minecraft("chests/village/village_armorer"))) {
            tryAddGem(event, GemTypes.jade);
        }
        if (key.equals(NamespacedKey.minecraft("chests/simple_dungeon"))) {
            tryAddGem(event, GemTypes.spider_fang);
        }
        if (key.equals(NamespacedKey.minecraft("chests/ruined_portal")) || key.equals(NamespacedKey.minecraft("chests/bastion_treasure")) || key.equals(NamespacedKey.minecraft("chests/ancient_city"))) {
            tryAddGem(event, GemTypes.ruby);
        }
        if (key.equals(NamespacedKey.minecraft("chests/desert_pyramid")) || key.equals(NamespacedKey.minecraft("chests/buried_treasure"))) {
            tryAddGem(event, GemTypes.lightstone);
        }
        if (key.equals(NamespacedKey.minecraft("chests/ancient_city")) || key.equals(NamespacedKey.minecraft("chests/ancient_city_ice_box"))) {
            tryAddGem(event, GemTypes.shadow_stone);
        }

    }

    private static void tryAddGem(LootGenerateEvent event, GemTypes gemType) {
        if (Math.random() <= JsonHandler.SpawnChanceReader(gemType.toString(), "chance")) {
            ItemStack gem = ItemHandler.createGem(gemType);
            event.getLoot().add(gem);
        }
    }
}
