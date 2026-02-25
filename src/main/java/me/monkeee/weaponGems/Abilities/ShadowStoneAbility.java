package me.monkeee.weaponGems.Abilities;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.GemTypes;
import me.monkeee.weaponGems.Handlers.TimerHandler;
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
                return nbt.getBoolean(GemTypes.shadow_stone.toString());
            });
        }

        if (!bootsHaveShadowStone) return;

        if (TimerHandler.isOnCooldown(player, GemTypes.shadow_stone, 40*1000)) return;

        TimerHandler.setCooldown(player, GemTypes.shadow_stone);

        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1, true, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 0, true, false));
        player.sendMessage(ChatColor.GREEN+"Your ability "+ChatColor.DARK_AQUA+"Last Echo"+ChatColor.GREEN+" has been "+ChatColor.WHITE+"activated!");
    }
}
