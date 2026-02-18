package me.monkeee.weaponGems.Abilities;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JadeAbility {

    public static void AbilityResistance(Player player) {
        ItemStack helmetSlot = player.getInventory().getHelmet();
        ItemStack chestplateSlot = player.getInventory().getChestplate();
        ItemStack leggingsSlot = player.getInventory().getLeggings();
        ItemStack bootsSlot = player.getInventory().getBoots();

        boolean helmetHasJadeGem = false;
        boolean chestplateHasJadeGem = false;
        boolean leggingsHasJadeGem = false;
        boolean bootsHasJadeGem = false;

        if (helmetSlot != null && helmetSlot.getType() != Material.AIR) {
            helmetHasJadeGem = NBT.get(helmetSlot, nbt -> {
                return nbt.getBoolean("jade_gem");
            });
        }

        if (chestplateSlot != null && chestplateSlot.getType() != Material.AIR) {
            chestplateHasJadeGem = NBT.get(chestplateSlot, nbt -> {
                return nbt.getBoolean("jade_gem");
            });
        }

        if (leggingsSlot != null && leggingsSlot.getType() != Material.AIR) {
            leggingsHasJadeGem = NBT.get(leggingsSlot, nbt -> {
                return nbt.getBoolean("jade_gem");
            });
        }

        if (bootsSlot != null && bootsSlot.getType() != Material.AIR) {
            bootsHasJadeGem = NBT.get(bootsSlot, nbt -> {
                return nbt.getBoolean("jade_gem");
            });
        }

        if (!(helmetHasJadeGem || chestplateHasJadeGem ||leggingsHasJadeGem || bootsHasJadeGem)) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 100, 1, true, false, false));
    }
}
