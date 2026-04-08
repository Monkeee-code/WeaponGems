package me.monkeee.weaponGems.Handlers;

import me.monkeee.weaponGems.API.GemDefinition;
import me.monkeee.weaponGems.API.GemRegistry;

import java.util.*;

/**
 * Kept for backward compatibility.
 * {@link #MapOfItems} is populated lazily from GemRegistry so existing code
 * (e.g. ApplyCommand's isCorrectItem check) continues to work unchanged.
 */
public class GemItemHandler {

    /** @deprecated Access GemRegistry directly — this view is rebuilt on each call. */
    @Deprecated
    public static Map<String, List<String>> MapOfItems = new HashMap<>();

    /**
     * Rebuilds MapOfItems from the current state of GemRegistry.
     * Called once after all gems are registered (built-in + addons).
     * @deprecated - Deprecated in favor of using GemRegistry.
     */
    @Deprecated
    public static void ApplyItemsToList() {
        MapOfItems.clear();
        for (GemDefinition gem : GemRegistry.getAll()) {
            MapOfItems.put(gem.getID(), gem.getApplicableItems());
        }
    }
}