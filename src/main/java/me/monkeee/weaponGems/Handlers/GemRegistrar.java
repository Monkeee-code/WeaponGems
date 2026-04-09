package me.monkeee.weaponGems.Handlers;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.API.GemDefinition;
import me.monkeee.weaponGems.API.GemRegistry;
import me.monkeee.weaponGems.Abilities.*;
import me.monkeee.weaponGems.WeaponGems;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads items.json and registers all built-in gems into {@link GemRegistry},
 * wiring up each gem's existing ability class.
 */
public class GemRegistrar {

    public static void registerAll(File dataFolder) {
        File itemsJson = new File(dataFolder, "items.json");
        FileConfiguration config = WeaponGems.getInstance().getConfig();

        JSONObject json;
        try {
            json = new JSONObject(Files.readString(itemsJson.toPath()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read items.json", e);
        }

        for (String gemId : json.keySet()) {
            if (config.isSet(gemId)) { if (!config.getBoolean(gemId)) return; }
            JSONObject gemObj = json.getJSONObject(gemId);

            String displayName = gemObj.getString("name");
            Material material  = Material.valueOf(gemObj.getString("item").toUpperCase());
            double spawnChance = gemObj.getJSONObject("spawnrate").getDouble("chance");

            // Lore
            List<String> lore = new ArrayList<>();
            JSONArray loreArray = gemObj.getJSONArray("lore");
            for (int i = 0; i < loreArray.length(); i++) {
                lore.add(loreArray.getString(i));
            }

            // Applicable items — read directly from JSON, no GemItemHandler needed
            List<String> applicableItems = new ArrayList<>();
            if (gemObj.has("applicable_items")) {
                JSONArray itemsArray = gemObj.getJSONArray("applicable_items");
                for (int i = 0; i < itemsArray.length(); i++) {
                    applicableItems.add(itemsArray.getString(i));
                }
            }

            GemDefinition.Builder builder = GemDefinition.builder(gemId)
                    .name(displayName)
                    .material(material)
                    .spawnChance(spawnChance)
                    .applicableTo(applicableItems.toArray(new String[0]));

            for (String line : lore) builder.lore(line);

            // Loot tables
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

    private static void wireAbilities(GemDefinition.Builder builder, String gemId) {
        switch (gemId) {
            case "jade"          -> builder.tickingAbility(JadeAbility::AbilityReinforcement);
            case "divan_core"    -> builder.tickingAbility(DivanCoreAbility::AbilityMinersFever);
            case "lightstone"    -> builder.tickingAbility(LightStoneAbility::AbilityLongevity);
            case "angel_feather" -> builder.tickingAbility(AngelFeatherAbility::AbilitySavingGrace);
            case "limitless_gem" -> builder.tickingAbility(LimitlessGemAbility::AbilityLimitBreaker);
            case "shadow_stone"  -> builder.damageAbility((victim, event) -> ShadowStoneAbility.AbilityLastEcho(victim));
            case "darkstone"     -> builder.dealerAbility((dealer, victim, event) -> DarkstoneAbility.AbilitySightDrain(dealer, victim));
            case "ruby"          -> builder.dealerAbility((dealer, victim, event) -> RubyAbility.AbilityLifeSteal(dealer, event));
            case "spider_fang"   -> builder.dealerAbility((dealer, victim, event) -> SpiderFangAbility.AbilityArachnidsFang(dealer, victim));
            case "deflection_eye" -> { /* arrow-specific, handled in EntityDamageEntityEvent */ }
            default -> { /* addon gems wired by their own plugin */ }
        }
    }

    /** Checks whether the given ItemStack has the specified gem applied. */
    public static boolean hasGem(org.bukkit.inventory.ItemStack item, String gemId) {
        return NBT.get(item, nbt -> (boolean) nbt.getBoolean(gemId));
    }
}