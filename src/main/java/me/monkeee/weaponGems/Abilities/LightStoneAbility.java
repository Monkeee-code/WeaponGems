package me.monkeee.weaponGems.Abilities;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.Handlers.TimerHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class LightStoneAbility {

    public static void AbilityRegeneration(Player player) {
        ItemStack chestplateSlot = player.getInventory().getChestplate();

        boolean chestplateHasJadeGem = false;

        if (chestplateSlot != null && chestplateSlot.getType() != Material.AIR) {
            chestplateHasJadeGem = NBT.get(chestplateSlot, nbt -> {
                return nbt.getBoolean("lightstone");
            });
        }

        if (!chestplateHasJadeGem) return;

        double maxHealth = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
        double health = player.getHealth();

        if ((maxHealth*0.25) < health) return;

        if (TimerHandler.isOnCooldown(player, "lightstone", 20000)) return;

        TimerHandler.setCooldown(player, "lightstone");

        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 1, true, false));
        player.sendMessage(ChatColor.RED+"Your "+ChatColor.YELLOW+"Lightstone"+ChatColor.RED+" ability has been "+ChatColor.GREEN+"activated!");
    }
}
