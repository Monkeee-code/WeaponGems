package me.monkeee.weaponGems.Commands;

import me.monkeee.weaponGems.GemID;
import me.monkeee.weaponGems.Handlers.CooldownHandler;
import me.monkeee.weaponGems.Handlers.ListHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class GetCooldownCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {
        if (!(sender instanceof Player player)) return false;
        player.sendMessage(ChatColor.YELLOW+"[?] Here are your cooldowns:");
        for (String gem : ListHandler.getGemList()) {
            String line = CooldownHandler.getCooldown(player, GemID.valueOf(gem));
            if (line.isBlank()) continue;
            player.sendMessage(line);
        }
        return true;
    }
}
