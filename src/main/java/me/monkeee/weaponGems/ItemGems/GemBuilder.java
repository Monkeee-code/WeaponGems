package me.monkeee.weaponGems.ItemGems;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.GemID;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.monkeee.weaponGems.Handlers.ItemHandler.applyApplicableItemsLore;

public class GemBuilder {

    public Material item;
    public List<String> lore;
    public String name;
    public String tag;
    public Class<? extends GemAbility> abilityClass;

    public GemBuilder(Material item) {
        this.item = item;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ItemStack getItem() {
        ItemStack gem = new ItemStack(this.item);
        ItemMeta gemMeta = gem.getItemMeta();

        if (lore.isEmpty() || name.isBlank() || tag.isEmpty()) {
            System.out.println("Please, make sure that the lore, tag and name are present!");
            return new ItemStack(Material.AIR);
        }

        assert gemMeta != null;
        gemMeta.setLore(lore);
        lore.add("");
        lore.add(ChatColor.DARK_GRAY+"Applicable Items:");
        applyApplicableItemsLore(GemID.valueOf(tag), lore);
        lore.add("");
        lore.add(ChatColor.DARK_GRAY+"GEM_ID: "+tag);
        gemMeta.setDisplayName(this.name);
        gemMeta.setMaxStackSize(1);
        gemMeta.setFireResistant(true);
        gem.setItemMeta(gemMeta);

        NBT.modify(gem, nbt -> {
            nbt.setBoolean("isGem", true);
            nbt.setString("gemType", this.tag);
        });

        return gem;
    }

    public void setAbility(Class<? extends GemAbility> abiltiyClass) {
        this.abilityClass = abiltiyClass;
    }

    public GemAbility createAbility() {
        if (abilityClass == null) return null;
        try {
            return abilityClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("Failed to instantiate abiltiy: "+ e.getMessage());
            return null;
        }
    }
}

