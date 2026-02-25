package me.monkeee.weaponGems.Events;

import me.monkeee.weaponGems.Abilities.ShadowStoneAbility;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnEntityDamageEvent implements Listener {

    @EventHandler
    public static void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player player) {
            ShadowStoneAbility.AbilityLastEcho(player);
        }
    }
}
