package me.monkeee.weaponGems.Abilities;

import de.tr7zw.changeme.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DeflectionEyeAbility {

    public static void AbilityDeflectArrows(Player player, Arrow arrow, EntityDamageByEntityEvent event) {
        ItemStack helmetSlot = player.getInventory().getHelmet();
        ItemStack chestplateSlot = player.getInventory().getChestplate();
        ItemStack leggingsSlot = player.getInventory().getLeggings();
        ItemStack bootsSlot = player.getInventory().getBoots();

        boolean helmetHasDeflectionEye = false;
        boolean chestplateHasDeflectionEye = false;
        boolean leggingsHasDeflectionEye = false;
        boolean bootsHasDeflectionEye = false;

        if (helmetSlot != null && helmetSlot.getType() != Material.AIR) {
            helmetHasDeflectionEye = NBT.get(helmetSlot, nbt -> {
                return nbt.getBoolean("deflection_eye");
            });
        }

        if (chestplateSlot != null && chestplateSlot.getType() != Material.AIR) {
            chestplateHasDeflectionEye = NBT.get(chestplateSlot, nbt -> {
                return nbt.getBoolean("deflection_eye");
            });
        }

        if (leggingsSlot != null && leggingsSlot.getType() != Material.AIR) {
            leggingsHasDeflectionEye = NBT.get(leggingsSlot, nbt -> {
                return nbt.getBoolean("deflection_eye");
            });
        }

        if (bootsSlot != null && bootsSlot.getType() != Material.AIR) {
            bootsHasDeflectionEye = NBT.get(bootsSlot, nbt -> {
                return nbt.getBoolean("deflection_eye");
            });
        }

        if (!(helmetHasDeflectionEye || chestplateHasDeflectionEye ||leggingsHasDeflectionEye || bootsHasDeflectionEye)) return;
        event.setCancelled(true);
        arrow.remove();
    }
}
