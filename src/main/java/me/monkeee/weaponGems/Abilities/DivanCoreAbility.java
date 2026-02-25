package me.monkeee.weaponGems.Abilities;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.GemTypes;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DivanCoreAbility {

    public static void AbilityHaste(Player player) {
        ItemStack tool = player.getInventory().getItemInMainHand();

        if (tool.getType().equals(Material.AIR)) return;

        boolean hasDivanGem = NBT.get(tool, nbt -> { return nbt.getBoolean(GemTypes.divan_core.toString()); });
        if (!hasDivanGem) return;

        player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 20, 1, true, false, false));
    }
}
