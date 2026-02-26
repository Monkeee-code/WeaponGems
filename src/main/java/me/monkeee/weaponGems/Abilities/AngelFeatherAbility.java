package me.monkeee.weaponGems.Abilities;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.GemTypes;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AngelFeatherAbility {

    public static void AbilitySavingGrace(Player player) {
        ItemStack bootsSlot = player.getInventory().getBoots();

        boolean bootsHaveAngelFeather = false;

        if (bootsSlot != null && bootsSlot.getType() != Material.AIR) {
            bootsHaveAngelFeather = NBT.get(bootsSlot, nbt -> {
                return nbt.getBoolean(GemTypes.angel_feather.toString());
            });
        }

        if (!bootsHaveAngelFeather) return;

        Location loc = player.getLocation();

        for (int i = 0; i < 4; i++) {
            if (loc.clone().add(0, -i, 0).getBlock().getType() != Material.AIR) {
                return;
            }
        }

        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 40, 0, false, false));
    }
}
