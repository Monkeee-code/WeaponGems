package me.monkeee.weaponGems.Events;

import me.monkeee.weaponGems.Abilities.*;
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
            DarkstoneAbility.AbilitySightDrain(dealer, player);
            RubyAbility.AbilityLifeSteal(dealer, event);
            SpiderFangAbility.AbilityArachnidsFang(dealer, player);
            LimitlessGemAbility.AbilityLimitBreaker(player);
        }
        if (entity1 instanceof Arrow arrow) {
            DeflectionEyeAbility.AbilityAichmophobia(player, arrow, event);
        }
    }
}
