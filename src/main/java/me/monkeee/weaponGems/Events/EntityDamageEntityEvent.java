package me.monkeee.weaponGems.Events;

import me.monkeee.weaponGems.Abilities.DarkstoneAbility;
import me.monkeee.weaponGems.Abilities.DeflectionEyeAbility;
import me.monkeee.weaponGems.Abilities.RubyAbility;
import me.monkeee.weaponGems.Abilities.SpiderSoulAbility;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageEntityEvent implements Listener {

    @EventHandler
    public static void onEntityDamage(EntityDamageByEntityEvent event) {
        Entity entity1 = event.getDamager();
        Entity entity2 = event.getEntity();

        if (!(entity2 instanceof Player player)) return;
        if (entity1 instanceof Player dealer) {
            DarkstoneAbility.AbilityBlindness(dealer, player);
            RubyAbility.AbilityLifeSteal(dealer, event);
            SpiderSoulAbility.AbilityPoison(dealer, player);
        }
        if (entity1 instanceof Arrow arrow) {
            DeflectionEyeAbility.AbilityDeflectArrows(player, arrow, event);
        }
    }
}
