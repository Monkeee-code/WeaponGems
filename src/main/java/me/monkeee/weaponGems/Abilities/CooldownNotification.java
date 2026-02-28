package me.monkeee.weaponGems.Abilities;

import me.monkeee.weaponGems.GemID;
import me.monkeee.weaponGems.Handlers.ListHandler;
import me.monkeee.weaponGems.WeaponGems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

import static me.monkeee.weaponGems.Handlers.CooldownHandler.cooldowns;
import static me.monkeee.weaponGems.Handlers.JsonHandler.String_reader;

public class CooldownNotification {

    public static void startNotificationTimer() {
        Bukkit.getScheduler().runTaskTimer(WeaponGems.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!cooldowns.containsKey(player.getUniqueId())) continue;

                Map<GemID, Long> abilities = cooldowns.get(player.getUniqueId());

                for (String gem : ListHandler.getGemList()) {
                    GemID gemT = GemID.valueOf(gem);

                    if (!abilities.containsKey(gemT)) continue;

                    long endTime = abilities.get(gemT);

                    if (System.currentTimeMillis() > endTime) {
                        player.sendMessage(ChatColor.GREEN+"Your gem "+ChatColor.translateAlternateColorCodes('&', String_reader(gem, "name"))+ChatColor.GREEN+" is off cooldown!");
                        abilities.remove(gemT);
                    }
                }
            }
        }, 0L, 10L);
    }
}
