package me.monkeee.weaponGems.Handlers;

import me.monkeee.weaponGems.GemID;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownHandler {

    // UUID -> (gemId -> expiry timestamp in millis)
    public static final Map<UUID, Map<String, Long>> cooldowns = new HashMap<>();

    // ---- String-based API (preferred, works for built-in and addon gems) ----

    public static boolean isOnCooldown(Player player, String gemId) {
        Map<String, Long> map = cooldowns.get(player.getUniqueId());
        if (map == null) return false;
        Long endTime = map.get(gemId);
        if (endTime == null) return false;
        return System.currentTimeMillis() < endTime;
    }

    public static void setCooldown(Player player, String gemId, long durationMillis) {
        cooldowns
                .computeIfAbsent(player.getUniqueId(), k -> new HashMap<>())
                .put(gemId, System.currentTimeMillis() + durationMillis);
    }

    public static String getCooldown(Player player, String gemId) {
        UUID uuid = player.getUniqueId();
        if (!cooldowns.containsKey(uuid)) return "";
        Map<String, Long> playerCooldowns = cooldowns.get(uuid);
        if (!playerCooldowns.containsKey(gemId)) return "";

        long endTime = playerCooldowns.get(gemId);
        long remaining = endTime - System.currentTimeMillis();
        if (remaining <= 0) return "";

        String displayName = JsonHandler.String_reader(gemId, "name");
        return ChatColor.translateAlternateColorCodes('&', displayName)
                + ChatColor.RESET + " -> " + Math.max(remaining / 1000, 0) + "s";
    }

    // ---- Legacy GemID enum overloads (kept for backward compat) ----

    public static boolean isOnCooldown(Player player, GemID ability) {
        return isOnCooldown(player, ability.toString());
    }

    public static void setCooldown(Player player, GemID ability, long durationMillis) {
        setCooldown(player, ability.toString(), durationMillis);
    }

    public static String getCooldown(Player player, GemID gem) {
        return getCooldown(player, gem.toString());
    }

    // ---- Cleanup ----

    public static void removePlayer(UUID uuid) {
        cooldowns.remove(uuid);
    }
}