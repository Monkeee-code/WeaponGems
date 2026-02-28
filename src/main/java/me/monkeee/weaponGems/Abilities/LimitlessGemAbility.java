package me.monkeee.weaponGems.Abilities;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.GemID;
import me.monkeee.weaponGems.Handlers.CooldownHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class LimitlessGemAbility {

    public static void AbilityLimitBreaker(Player player) {
        ItemStack chestplateSlot = player.getInventory().getChestplate();

        boolean chestplateHasLimitlessGem = false;

        if (chestplateSlot != null && chestplateSlot.getType() != Material.AIR) {
            chestplateHasLimitlessGem = NBT.get(chestplateSlot, nbt -> {
                return nbt.getBoolean(GemID.limitless_gem.toString());
            });
        }

        if (!chestplateHasLimitlessGem) return;

        double maxHealth = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
        double currentHealth = player.getHealth();

        if ((maxHealth*0.1) < currentHealth) return;

        if (CooldownHandler.isOnCooldown(player, GemID.limitless_gem)) return;

        CooldownHandler.setCooldown(player, GemID.limitless_gem, 300*1000);

        player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 200, 2, true, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2, true, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 0, true, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 200, 1, true, false));
        player.sendMessage(ChatColor.GREEN+"[!] Your ability "+ChatColor.DARK_RED+"Limit Breaker"+ChatColor.GREEN+" has been "+ChatColor.WHITE+"activated!");
    }
}
