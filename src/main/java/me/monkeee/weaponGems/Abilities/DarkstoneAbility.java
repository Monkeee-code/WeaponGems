package me.monkeee.weaponGems.Abilities;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.GemID;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DarkstoneAbility {

    public static void AbilitySightDrain(Player dealer, Player player) {
        if (dealer.getInventory().getItemInMainHand().getType().equals(Material.AIR)) return;
        ItemStack weapon = dealer.getInventory().getItemInMainHand();

        if (weapon.getType().equals(Material.AIR)) return;

        boolean hasDarkstoneGem = NBT.get(weapon, nbt -> {
            return nbt.getBoolean(GemID.darkstone.toString());
        });

        if (!hasDarkstoneGem) return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1, false, false, true));
    }
}
