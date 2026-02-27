package me.monkeee.weaponGems.Handlers;

import me.monkeee.weaponGems.GemTypes;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CooldownHandler {

    public static final Map<UUID, Map<GemTypes, Long>> cooldowns = new HashMap<>();

    public static boolean isOnCooldown(Player player, GemTypes ability) {

        Map<GemTypes, Long> map = cooldowns.get(player.getUniqueId());
        if (map == null) return false;

        Long endTime = map.get(ability);
        if (endTime == null) return false;

        return System.currentTimeMillis() < endTime;
    }

    public static void setCooldown(Player player, GemTypes ability, long durationMillis) {
        cooldowns
                .computeIfAbsent(player.getUniqueId(), k -> new HashMap<>())
                .put(ability, System.currentTimeMillis() + durationMillis);
    }

    public static String getCooldown(Player player, GemTypes gem) {

        UUID uuid = player.getUniqueId();

        if (!cooldowns.containsKey(uuid)) return "";

        Map<GemTypes, Long> playerCooldowns = cooldowns.get(uuid);

        if (!playerCooldowns.containsKey(gem)) return "";

        long endTime = playerCooldowns.get(gem);
        long remaining = endTime - System.currentTimeMillis();

        return ChatColor.translateAlternateColorCodes('&', JsonHandler.String_reader(gem.toString(), "name")+ChatColor.RESET+" -> "+Math.max(remaining/1000, 0) +"s");
    }
}
