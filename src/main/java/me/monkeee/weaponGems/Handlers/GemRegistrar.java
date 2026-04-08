package me.monkeee.weaponGems.Handlers;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.API.GemDefinition;
import me.monkeee.weaponGems.API.GemRegistry;
import me.monkeee.weaponGems.GemID;
import me.monkeee.weaponGems.Abilities.*;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads items.json and registers all built-in gems into {@link GemRegistry},
 * wiring up each gem's existing ability class.
 *
 * This replaces the old GemItemHandler map and the scattered hardcoded lists.
 */
public class GemRegistrar {

    public static void registerAll(File dataFolder) {
        File itemsJson = new File(dataFolder, "items.json");

        JSONObject json;
        try {
            json = new JSONObject(Files.readString(itemsJson.toPath()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read items.json", e);
        }

        for (String gemId : json.keySet()) {
            JSONObject gemObj = json.getJSONObject(gemId);

            String displayName = gemObj.getString("name");
            Material material  = Material.valueOf(gemObj.getString("item").toUpperCase());
            double spawnChance = gemObj.getJSONObject("spawnrate").getDouble("chance");

            List<String> lore = new ArrayList<>();
            JSONArray loreArray = gemObj.getJSONArray("lore");
            for (int i = 0; i < loreArray.length(); i++) {
                lore.add(loreArray.getString(i));
            }

            List<String> applicableItems = GemItemHandler.MapOfItems.getOrDefault(gemId, List.of());

            GemDefinition.Builder builder = GemDefinition.builder(gemId)
                    .name(displayName)
                    .material(material)
                    .spawnChance(spawnChance)
                    .applicableTo(applicableItems.toArray(new String[0]));

            for (String line : lore) builder.lore(line);

            // Read loot table keys from JSON, e.g. "loot_tables": ["chests/simple_dungeon"]
            if (gemObj.has("loot_tables")) {
                JSONArray tables = gemObj.getJSONArray("loot_tables");
                for (int i = 0; i < tables.length(); i++) {
                    builder.lootTables(NamespacedKey.minecraft(tables.getString(i)));
                }
            }

            wireAbilities(builder, gemId);

            GemRegistry.register(builder.build());
        }
    }

    /**
     * Wires the existing ability classes to the three ability slots.
     * When you add a new built-in gem, add a case here.
     */
    private static void wireAbilities(GemDefinition.Builder builder, String gemId) {
        switch (gemId) {

            case "jade" -> builder.tickingAbility(JadeAbility::AbilityReinforcement);

            case "divan_core" -> builder.tickingAbility(DivanCoreAbility::AbilityMinersFever);

            case "lightstone" -> builder.tickingAbility(LightStoneAbility::AbilityLongevity);

            case "angel_feather" -> builder.tickingAbility(AngelFeatherAbility::AbilitySavingGrace);

            case "limitless_gem" -> builder.tickingAbility(LimitlessGemAbility::AbilityLimitBreaker);

            case "shadow_stone" -> builder.damageAbility((victim, event) ->
                    ShadowStoneAbility.AbilityLastEcho(victim));

            case "darkstone" -> builder.dealerAbility((dealer, victim, event) ->
                    DarkstoneAbility.AbilitySightDrain(dealer, victim));

            case "ruby" -> builder.dealerAbility((dealer, victim, event) ->
                    RubyAbility.AbilityLifeSteal(dealer, event));

            case "spider_fang" -> builder.dealerAbility((dealer, victim, event) ->
                    SpiderFangAbility.AbilityArachnidsFang(dealer, victim));

            // deflection_eye is arrow-specific and handled directly in EntityDamageEntityEvent
            case "deflection_eye" -> { /* no ability slot needed */ }

            default -> { /* addon gems wired by their own plugin */ }
        }
    }

    // -----------------------------------------------------------------------
    // Helpers used by GemItemHandler (backward compat) and ApplyCommand
    // -----------------------------------------------------------------------

    /** Returns the applicable item suffixes for a gem, pulled from GemRegistry. */
    public static List<String> getApplicableItems(String gemId) {
        return GemRegistry.get(gemId)
                .map(GemDefinition::getApplicableItems)
                .orElse(List.of());
    }

    /** Checks whether the given ItemStack has the specified gem applied. */
    public static boolean hasGem(org.bukkit.inventory.ItemStack item, String gemId) {
        return NBT.get(item, nbt -> (boolean) nbt.getBoolean(gemId));
    }
}