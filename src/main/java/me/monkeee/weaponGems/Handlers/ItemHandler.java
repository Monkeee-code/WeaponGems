package me.monkeee.weaponGems.Handlers;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemHandler {

    public static ItemStack createGem(String gem_type) {
        ItemStack gem = new ItemStack(Material.valueOf(JsonHandler.String_reader(gem_type, "item").toUpperCase()));
        ItemMeta gem_meta = gem.getItemMeta();
        assert gem_meta != null;
        gem_meta.setEnchantmentGlintOverride(true);
        gem_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', JsonHandler.String_reader(gem_type, "name")));

        List<String> Lore = new ArrayList<>();
        for (int i = 0; i< JsonHandler.ListReader(gem_type).length(); i++){
            Lore.add(ChatColor.translateAlternateColorCodes('&', JsonHandler.ListReader(gem_type).getString(i)));
        }
        Lore.add("");
        Lore.add(ChatColor.DARK_GRAY+"Applicable Items:");
        applyApplicableItemsLore(gem_type, Lore);
        gem_meta.setLore(Lore);
        gem.setItemMeta(gem_meta);

        NBT.modify(gem, nbt -> {
            nbt.setBoolean("isGem", true);
            nbt.setString("gemType", JsonHandler.String_reader(gem_type, "tag"));
        });
        return gem;
    }

    private static void applyApplicableItemsLore(String gemType, List<String> lore) {
        if (gemType.equalsIgnoreCase("darkstone")) {
            lore.addAll(GemItemHandler.DarkstoneItems);
        } else if (gemType.equalsIgnoreCase("deflection_exe")) {
            lore.addAll(GemItemHandler.DeflectionEyeItems);
        } else if (gemType.equalsIgnoreCase("divan_core")) {
            lore.addAll(GemItemHandler.DivanCoreItems);
        } else if (gemType.equalsIgnoreCase("jade")) {
            lore.addAll(GemItemHandler.JadeItems);
        } else if (gemType.equalsIgnoreCase("ruby")) {
            lore.addAll(GemItemHandler.RubyItems);
        } else if (gemType.equalsIgnoreCase("spider_fang")) {
            lore.addAll(GemItemHandler.SpiderFangItems);
        }
    }
}
