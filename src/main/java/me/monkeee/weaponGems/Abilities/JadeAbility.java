package me.monkeee.weaponGems.Abilities;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.GemTypes;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JadeAbility {

    public static void AbilityReinforcement(Player player) {
        ItemStack chestplateSlot = player.getInventory().getChestplate();

        boolean chestplateHasJadeGem = false;

        if (chestplateSlot != null && chestplateSlot.getType() != Material.AIR) {
            chestplateHasJadeGem = NBT.get(chestplateSlot, nbt -> {
                return nbt.getBoolean(GemTypes.jade.toString());
            });
        }

        if (!chestplateHasJadeGem) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 100, 0, true, false, false));
    }
}
