package me.monkeee.weaponGems.API;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@FunctionalInterface
public interface GemDealerAbility {
    void onDamageDealt(Player dealer, Player victim, EntityDamageByEntityEvent event);
}
