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
        gem_meta.setMaxStackSize(1);
        gem_meta.setFireResistant(true);
        gem.setItemMeta(gem_meta);

        NBT.modify(gem, nbt -> {
            nbt.setBoolean("isGem", true);
            nbt.setString("gemType", JsonHandler.String_reader(gem_type, "tag"));
        });
        return gem;
    }

    private static void applyApplicableItemsLore(String gemType, List<String> lore) {
        if (gemType.equalsIgnoreCase("darkstone")) {
            for (String line : GemItemHandler.DarkstoneItems) {
                lore.add(ChatColor.GRAY+line);
            }
        } else if (gemType.equalsIgnoreCase("deflection_eye")) {
            for (String line : GemItemHandler.DeflectionEyeItems) {
                lore.add(ChatColor.GRAY+line);
            }
        } else if (gemType.equalsIgnoreCase("divan_core")) {
            for (String line : GemItemHandler.DivanCoreItems) {
                lore.add(ChatColor.GRAY+line);
            }
        } else if (gemType.equalsIgnoreCase("jade")) {
            for (String line : GemItemHandler.JadeItems) {
                lore.add(ChatColor.GRAY+line);
            }
        } else if (gemType.equalsIgnoreCase("ruby")) {
            for (String line : GemItemHandler.RubyItems) {
                lore.add(ChatColor.GRAY+line);
            }
        } else if (gemType.equalsIgnoreCase("spider_fang")) {
            for (String line : GemItemHandler.SpiderFangItems) {
                lore.add(ChatColor.GRAY+line);
            }
        }
    }
}
