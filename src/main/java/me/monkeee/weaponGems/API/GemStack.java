package me.monkeee.weaponGems.API;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GemStack {

    private GemStack() {}

    public static ItemStack createFrom(GemDefinition definition) {
        ItemStack gem = new ItemStack(definition.getMaterial());
        ItemMeta meta = gem.getItemMeta();

        if (meta == null) {
            throw new IllegalStateException("Could not get ItemMeta for material: "+definition.getMaterial());
        }

        meta.setEnchantmentGlintOverride(true);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', definition.getDisplayName()));
        meta.setMaxStackSize(1);
        meta.setFireResistant(true);

        List<String> lore = buildLore(definition);
        meta.setLore(lore);
        gem.setItemMeta(meta);

        String gemID = definition.getID();
        NBT.modify(gem, NBT -> {
            NBT.setBoolean("isGem", true);
            NBT.setString("gemType", gemID);
        });

        return gem;
    }

    private static List<String> buildLore(GemDefinition definition) {
        List<String> lore = new ArrayList<>();

        for (String line : definition.getLore()) {
            lore.add(ChatColor.translateAlternateColorCodes('&', line));
        }

        lore.add("");
        lore.add(ChatColor.DARK_GRAY+"Applicable Items:");
        for (String applicable : definition.getApplicableItems()) {
            lore.add(ChatColor.GRAY+applicable);
        }

        lore.add("");
        lore.add(ChatColor.DARK_GRAY+"GEM_ID: "+definition.getID());

        return lore;
    }

    public static void appendApplicableItemsLore(String gemId, List<String> lore) {
        List<String> items = GemRegistry.get(gemId).get().getApplicableItems();
        if (items != null) {
            for (String item : items) {
                lore.add(ChatColor.GRAY + item);
            }
        }
    }
}
