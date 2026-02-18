package me.monkeee.weaponGems.Abilities;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class RubyAbility {

    public static void AbilityLifeSteal(Player dealer, EntityDamageByEntityEvent event) {
        ItemStack dealerWeapon = dealer.getInventory().getItemInMainHand();
        boolean hasRubyGem = NBT.get(dealerWeapon, nbt -> { return nbt.getBoolean("ruby_gem"); });
        if (!hasRubyGem) return;
        double dealerHP = dealer.getHealth();
        double damage = event.getFinalDamage();
        double maxHealth = Objects.requireNonNull(dealer.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
        dealer.setHealth(Math.min(dealerHP+(damage*0.15), maxHealth));
    }
}
