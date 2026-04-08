package me.monkeee.weaponGems.API;

import java.util.*;

public class GemRegistry {

    private static Map<String, GemDefinition> registry = new LinkedHashMap<>();

    public static void register(GemDefinition definition) {
        String id = definition.getID();
        if (registry.containsKey(id)) {
            throw new IllegalArgumentException("A gem with ID "+id+" is already registered");
        }
        registry.put(id, definition);
    }

    public static void unregister(String gemID) {
        registry.remove(gemID);
    }

    public static Optional<GemDefinition> get(String gemID) {
        return Optional.ofNullable(registry.get(gemID));
    }

    public static boolean isRegistered(String gemID) {
        return registry.containsKey(gemID);
    }

    public static Set<String> getAllIDs() {
        return Collections.unmodifiableSet(registry.keySet());
    }

    public static Collection<GemDefinition> getAll() {
        return Collections.unmodifiableCollection(registry.values());
    }

    public static void clear() {
        registry.clear();
    }
}
