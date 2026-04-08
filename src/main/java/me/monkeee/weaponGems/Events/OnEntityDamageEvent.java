package me.monkeee.weaponGems.Events;

import me.monkeee.weaponGems.API.GemDamageAbility;
import me.monkeee.weaponGems.API.GemDefinition;
import me.monkeee.weaponGems.API.GemRegistry;
import me.monkeee.weaponGems.WeaponGems;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnEntityDamageEvent implements Listener {

    @EventHandler
    public static void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player player)) return;

        for (GemDefinition gem : GemRegistry.getAll()) {
            GemDamageAbility ability = gem.getDamageAbility();
            if (ability != null) {
                try {
                    ability.onDamaged(player, event);
                } catch (Exception e) {
                    WeaponGems.getInstance().getLogger().warning(
                            "Error in damage ability for gem '" + gem.getID() + "': " + e.getMessage()
                    );
                }
            }
        }
    }
}