package me.monkeee.weaponGems.Abilities;

import me.monkeee.weaponGems.WeaponGems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static me.monkeee.weaponGems.Handlers.CooldownHandler.cooldowns;
import static me.monkeee.weaponGems.Handlers.JsonHandler.String_reader;

public class CooldownNotification {

    public static void startNotificationTimer() {
        Bukkit.getScheduler().runTaskTimer(WeaponGems.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                UUID uuid = player.getUniqueId();
                if (!cooldowns.containsKey(uuid)) continue;

                Map<String, Long> abilities = cooldowns.get(uuid);

                // Iterate over a copy to allow safe removal
                new HashMap<>(abilities).forEach((gemId, endTime) -> {
                    if (System.currentTimeMillis() > endTime) {
                        String displayName;
                        try {
                            displayName = String_reader(gemId, "name");
                        } catch (Exception e) {
                            displayName = gemId; // fallback for addon gems with no JSON entry
                        }
                        player.sendMessage(ChatColor.GREEN + "Your gem "
                                + ChatColor.translateAlternateColorCodes('&', displayName)
                                + ChatColor.GREEN + " is off cooldown!");
                        abilities.remove(gemId);
                    }
                });
            }
        }, 0L, 10L);
    }
}