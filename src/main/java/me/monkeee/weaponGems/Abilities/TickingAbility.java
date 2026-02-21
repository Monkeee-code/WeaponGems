package me.monkeee.weaponGems.Abilities;

import me.monkeee.weaponGems.WeaponGems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TickingAbility {

    public static void startTicking() {
        WeaponGems.getInstance().getLogger().info("The Ticking has begun!");
        WeaponGems.getInstance().getLogger().info("Some abilities are ran every second!");
        Bukkit.getScheduler().runTaskTimer(WeaponGems.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                JadeAbility.AbilityResistance(player);
                DivanCoreAbility.AbilityHaste(player);

            }
        }, 0L, 20L);
    }
}
