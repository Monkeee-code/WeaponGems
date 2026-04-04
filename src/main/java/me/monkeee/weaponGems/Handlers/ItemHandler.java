package me.monkeee.weaponGems.Handlers;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.GemID;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemHandler {

    public static ItemStack createGem(GemID gem_type) {
        ItemStack gem = new ItemStack(Material.valueOf(JsonHandler.String_reader(gem_type.toString(), "item").toUpperCase()));
        ItemMeta gem_meta = gem.getItemMeta();
        assert gem_meta != null;
        gem_meta.setEnchantmentGlintOverride(true);
        gem_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', JsonHandler.String_reader(gem_type.toString(), "name")));

        List<String> Lore = new ArrayList<>();
        for (int i = 0; i< JsonHandler.ListReader(gem_type.toString()).length(); i++){
            Lore.add(ChatColor.translateAlternateColorCodes('&', JsonHandler.ListReader(gem_type.toString()).getString(i)));
        }
        Lore.add("");
        Lore.add(ChatColor.DARK_GRAY+"Applicable Items:");
        applyApplicableItemsLore(gem_type, Lore);
        Lore.add("");
        Lore.add(ChatColor.DARK_GRAY+"GEM_ID: "+gem_type);
        gem_meta.setLore(Lore);
        gem_meta.setMaxStackSize(1);
        gem_meta.setFireResistant(true);
        gem.setItemMeta(gem_meta);

        NBT.modify(gem, nbt -> {
            nbt.setBoolean("isGem", true);
            nbt.setString("gemType", JsonHandler.String_reader(gem_type.toString(), "tag"));
        });
        return gem;
    }

    public static void applyApplicableItemsLore(GemID gemType, List<String> lore) {
        for (String line : GemItemHandler.MapOfItems.get(gemType.toString())) {
            lore.add(ChatColor.GRAY+line);
        }
    }
}
