package me.monkeee.weaponGems.Abilities;

import me.monkeee.weaponGems.WeaponGems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TickingAbility {

    public static void startTicking() {
        Bukkit.getScheduler().runTaskTimer(WeaponGems.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                JadeAbility.AbilityResistance(player);
                DivanCoreAbility.AbilityHaste(player);

            }
        }, 0L, 20L);
    }
}
