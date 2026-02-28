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

public class LightStoneAbility {

    public static void AbilityLongevity(Player player) {
        ItemStack chestplateSlot = player.getInventory().getChestplate();

        boolean chestplateHasJadeGem = false;

        if (chestplateSlot != null && chestplateSlot.getType() != Material.AIR) {
            chestplateHasJadeGem = NBT.get(chestplateSlot, nbt -> {
                return nbt.getBoolean(GemID.lightstone.toString());
            });
        }

        if (!chestplateHasJadeGem) return;

        double maxHealth = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
        double health = player.getHealth();

        if ((maxHealth*0.25) < health) return;

        if (CooldownHandler.isOnCooldown(player, GemID.lightstone)) return;

        CooldownHandler.setCooldown(player, GemID.lightstone, 20*1000);

        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 1, true, false));
        player.sendMessage(ChatColor.GREEN+"[!] Your ability "+ChatColor.YELLOW+"Longevity"+ChatColor.GREEN+" has been "+ChatColor.WHITE+"activated!");
    }
}
