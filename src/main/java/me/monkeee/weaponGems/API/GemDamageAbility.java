package me.monkeee.weaponGems.API;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

@FunctionalInterface
public interface GemDamageAbility {
    void onDamaged(Player victim, EntityDamageEvent event);
}
