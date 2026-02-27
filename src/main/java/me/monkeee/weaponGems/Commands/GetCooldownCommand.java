package me.monkeee.weaponGems.Commands;

import me.monkeee.weaponGems.GemTypes;
import me.monkeee.weaponGems.Handlers.CooldownHandler;
import me.monkeee.weaponGems.Handlers.ListHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetCooldownCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return false;
        player.sendMessage(ChatColor.YELLOW+"Here are your cooldowns:");
        for (String gem : ListHandler.getGemList()) {
            String line = CooldownHandler.getCooldown(player, GemTypes.valueOf(gem));
            if (line.isBlank()) continue;
            player.sendMessage(line);
        }
        return true;
    }
}
