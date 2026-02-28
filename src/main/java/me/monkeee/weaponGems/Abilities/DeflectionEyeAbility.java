package me.monkeee.weaponGems.Abilities;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.GemID;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DeflectionEyeAbility {

    public static void AbilityAichmophobia(Player player, Arrow arrow, EntityDamageByEntityEvent event) {
        ItemStack helmetSlot = player.getInventory().getHelmet();

        boolean helmetHasDeflectionEye = false;


        if (helmetSlot != null && helmetSlot.getType() != Material.AIR) {
            helmetHasDeflectionEye = NBT.get(helmetSlot, nbt -> {
                return nbt.getBoolean(GemID.deflection_eye.toString());
            });
        }


        if (!helmetHasDeflectionEye) return;
        event.setCancelled(true);
        arrow.remove();
    }
}
