package me.monkeee.weaponGems.Abilities;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpiderSoulAbility {

    public static void AbilityPoison(Player dealer, Player player) {
        ItemStack weapon = dealer.getInventory().getItemInMainHand();

        if (weapon.getType().equals(Material.AIR)) return;
        boolean hasSpiderSoulGem = NBT.get(weapon, nbt -> { return nbt.getBoolean("spider_soul"); });
        if (!hasSpiderSoulGem) return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20, 1, false, false, true));
    }
}
