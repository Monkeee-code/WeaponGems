package me.monkeee.weaponGems.Handlers;

import me.monkeee.weaponGems.GemTypes;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TimerHandler {

    public static Map<UUID, Map<GemTypes, Long>> cooldowns = new HashMap<>();

    public static boolean isOnCooldown(Player player, GemTypes ability, long cooldown) {
        UUID uuid = player.getUniqueId();

        cooldowns.putIfAbsent(uuid, new HashMap<>());

        long currentTime = System.currentTimeMillis();
        long lastUsed = cooldowns.get(uuid).getOrDefault(ability, 0L);

        return (currentTime - lastUsed) < cooldown;
    }

    public static void setCooldown(Player player, GemTypes ability) {
        cooldowns.get(player.getUniqueId())
                .put(ability, System.currentTimeMillis());
    }
}
