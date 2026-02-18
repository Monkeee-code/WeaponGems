package me.monkeee.weaponGems.Abilities;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DarkstoneAbility {

    public static void AbilityBlindness(Player dealer, Player player) {
        ItemStack weapon = dealer.getInventory().getItemInMainHand();

        if (weapon.getType().equals(Material.AIR)) return;

        boolean hasDarkstoneGem = NBT.get(weapon, nbt -> { return nbt.getBoolean("darkstone_gem"); });

        if (!hasDarkstoneGem) return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 1, false, false, true));
    }
}
