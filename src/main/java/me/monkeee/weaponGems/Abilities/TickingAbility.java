package me.monkeee.weaponGems.Abilities;

import me.monkeee.weaponGems.API.GemDefinition;
import me.monkeee.weaponGems.API.GemRegistry;
import me.monkeee.weaponGems.API.GemTickingAbility;
import me.monkeee.weaponGems.WeaponGems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TickingAbility {

    public static void startTicking() {
        WeaponGems.getInstance().getLogger().info("The Ticking has begun!");
        WeaponGems.getInstance().getLogger().info("Registered gem abilities run every 10 ticks.");

        Bukkit.getScheduler().runTaskTimer(WeaponGems.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                for (GemDefinition gem : GemRegistry.getAll()) {
                    GemTickingAbility ability = gem.getTickingAbility();
                    if (ability != null) {
                        try {
                            ability.onTick(player);
                        } catch (Exception e) {
                            WeaponGems.getInstance().getLogger().warning(
                                    "Error in ticking ability for gem '" + gem.getID() + "': " + e.getMessage()
                            );
                        }
                    }
                }
            }
        }, 0L, 10L);
    }
}