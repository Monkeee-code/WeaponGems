package me.monkeee.weaponGems.Events;

import me.monkeee.weaponGems.GemID;
import me.monkeee.weaponGems.Handlers.ItemHandler;
import me.monkeee.weaponGems.Handlers.JsonHandler;
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

        if (key.equals(NamespacedKey.minecraft("chests/village/village_toolsmith"))) {
            tryAddGem(event, GemID.divan_core);
        }
        if (key.equals(NamespacedKey.minecraft("chests/end_city_treasure"))) {
            tryAddGem(event, GemID.deflection_eye);
        }
        if (key.equals(NamespacedKey.minecraft("chests/village/village_weaponsmith"))) {
            tryAddGem(event, GemID.darkstone);
        }
        if (key.equals(NamespacedKey.minecraft("chests/village/village_armorer"))) {
            tryAddGem(event, GemID.jade);
        }
        if (key.equals(NamespacedKey.minecraft("chests/simple_dungeon"))) {
            tryAddGem(event, GemID.spider_fang);
        }
        if (key.equals(NamespacedKey.minecraft("chests/ruined_portal")) || key.equals(NamespacedKey.minecraft("chests/bastion_treasure")) || key.equals(NamespacedKey.minecraft("chests/ancient_city"))) {
            tryAddGem(event, GemID.ruby);
        }
        if (key.equals(NamespacedKey.minecraft("chests/desert_pyramid")) || key.equals(NamespacedKey.minecraft("chests/buried_treasure"))) {
            tryAddGem(event, GemID.lightstone);
        }
        if (key.equals(NamespacedKey.minecraft("chests/ancient_city")) || key.equals(NamespacedKey.minecraft("chests/ancient_city_ice_box"))) {
            tryAddGem(event, GemID.shadow_stone);
        }
        if (key.equals(NamespacedKey.minecraft("chests/desert_pyramid"))) {
            tryAddGem(event, GemID.angel_feather);
        }
        if (key.equals(NamespacedKey.minecraft("chests/woodland_mansion"))) {
            tryAddGem(event, GemID.limitless_gem);
        }

    }

    private static void tryAddGem(LootGenerateEvent event, GemID gemType) {
        if (Math.random() <= JsonHandler.SpawnChanceReader(gemType.toString(), "chance")) {
            ItemStack gem = ItemHandler.createGem(gemType);
            event.getLoot().add(gem);
        }
    }
}
