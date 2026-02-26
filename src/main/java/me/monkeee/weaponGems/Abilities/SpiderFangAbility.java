package me.monkeee.weaponGems.Abilities;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.GemTypes;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpiderFangAbility {

    public static void AbilityArachnidsFang(Player dealer, Player player) {
        if (dealer.getInventory().getItemInMainHand().getType().equals(Material.AIR)) return;
        ItemStack weapon = dealer.getInventory().getItemInMainHand();

        if (weapon.getType().equals(Material.AIR)) return;
        boolean hasSpiderSoulGem = NBT.get(weapon, nbt -> { return nbt.getBoolean(GemTypes.spider_fang.toString()); });
        if (!hasSpiderSoulGem) return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1, false, false, true));
    }
}
