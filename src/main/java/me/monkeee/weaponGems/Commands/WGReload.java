package me.monkeee.weaponGems.Commands;

import me.monkeee.weaponGems.WeaponGems;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WGReload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "[!] You're not an op! :(");
            return false;
        }
        WeaponGems.getInstance().reloadConfig();
        sender.sendMessage(ChatColor.GREEN+"[!] The config has been reloaded successfully!");
        return true;
    }
}
