package me.monkeee.weaponGems.Events;

import me.monkeee.weaponGems.API.GemDefinition;
import me.monkeee.weaponGems.API.GemDealerAbility;
import me.monkeee.weaponGems.API.GemRegistry;
import me.monkeee.weaponGems.Abilities.DeflectionEyeAbility;
import me.monkeee.weaponGems.WeaponGems;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageEntityEvent implements Listener {

    @EventHandler
    public static void onEntityDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity victim  = event.getEntity();

        if (!(victim instanceof Player playerVictim)) return;

        // Dealer-side abilities: iterate every registered gem
        if (damager instanceof Player playerDealer) {
            for (GemDefinition gem : GemRegistry.getAll()) {
                GemDealerAbility ability = gem.getDealerAbility();
                if (ability != null) {
                    try {
                        ability.onDamageDealt(playerDealer, playerVictim, event);
                    } catch (Exception e) {
                        WeaponGems.getInstance().getLogger().warning(
                                "Error in dealer ability for gem '" + gem.getID() + "': " + e.getMessage()
                        );
                    }
                }
            }
        }

        // Arrow-specific abilities remain hardcoded (deflection_eye checks its own slot)
        if (damager instanceof Arrow arrow) {
            DeflectionEyeAbility.AbilityAichmophobia(playerVictim, arrow, event);
        }
    }
}