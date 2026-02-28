package me.monkeee.weaponGems.Abilities;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.GemID;
import me.monkeee.weaponGems.Handlers.CooldownHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ShadowStoneAbility {

    public static void AbilityLastEcho(Player player) {
        ItemStack bootsSlot = player.getInventory().getBoots();

        boolean bootsHaveShadowStone = false;

        if (bootsSlot != null && bootsSlot.getType() != Material.AIR) {
            bootsHaveShadowStone = NBT.get(bootsSlot, nbt -> {
                return nbt.getBoolean(GemID.shadow_stone.toString());
            });
        }

        if (!bootsHaveShadowStone) return;

        if (CooldownHandler.isOnCooldown(player, GemID.shadow_stone)) return;

        CooldownHandler.setCooldown(player, GemID.shadow_stone, 40*1000);

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1, true, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 0, true, false));
        player.sendMessage(ChatColor.GREEN+"[!] Your ability "+ChatColor.DARK_AQUA+"Last Echo"+ChatColor.GREEN+" has been "+ChatColor.WHITE+"activated!");
    }
}
