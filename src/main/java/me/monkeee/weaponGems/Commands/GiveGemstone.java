package me.monkeee.weaponGems.Commands;

import me.monkeee.weaponGems.Handlers.ItemHandler;
import me.monkeee.weaponGems.Handlers.JsonHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class GiveGemstone implements CommandExecutor {
    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!sender.isOp()) { sender.sendMessage(ChatColor.RED + "Only Operators can execute this command!"); return false; }
        ((Player) sender).getInventory().addItem(ItemHandler.createGem(args[0]));
        sender.sendMessage(ChatColor.GREEN+"You have been given a gem:"+ChatColor.translateAlternateColorCodes('&', JsonHandler.String_reader(args[0], "name")));
        return false;
    }
}
