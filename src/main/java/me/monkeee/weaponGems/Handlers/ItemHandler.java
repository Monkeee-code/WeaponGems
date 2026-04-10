package me.monkeee.weaponGems.Handlers;

import me.monkeee.weaponGems.API.GemRegistry;
import me.monkeee.weaponGems.API.GemStack;
import me.monkeee.weaponGems.GemID;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemHandler {

    /**
     * Creates a gem ItemStack by gem ID string.
     * Works for both built-in gems (loaded from JSON) and addon-registered gems.
     */
    public static ItemStack createGem(String gemId) {
        return GemRegistry.get(gemId)
                .map(GemStack::createFrom)
                .orElseThrow(() -> new IllegalArgumentException("No gem registered with ID: " + gemId));
    }

    /**
     * Appends "Applicable Items:" lore lines to the given list.
     * @deprecated Prefer using GemStack.createFrom(definition) which handles lore automatically.
     */
    @Deprecated
    public static void applyApplicableItemsLore(GemID gemType, List<String> lore) {
        GemStack.appendApplicableItemsLore(gemType.toString(), lore);
    }
}