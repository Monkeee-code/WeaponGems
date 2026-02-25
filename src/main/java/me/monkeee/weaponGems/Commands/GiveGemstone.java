package me.monkeee.weaponGems.Commands;

import me.monkeee.weaponGems.Handlers.ItemHandler;
import me.monkeee.weaponGems.Handlers.JsonHandler;
import me.monkeee.weaponGems.Handlers.ListHandler;
import me.monkeee.weaponGems.WeaponGems;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GiveGemstone implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!(sender instanceof Player player)) return false;
        if (!player.isOp()) { player.sendMessage(ChatColor.RED + "Only Operators can execute this command!"); return false; }
        if (args[0].isEmpty()) { player.sendMessage(ChatColor.RED + "Please, mention a gem to use!"); return false; }
        if (!ListHandler.getGemList().contains(args[0])) { player.sendMessage(ChatColor.RED+"Gem \""+args[0]+"\" does not exist!"); return false; }
        player.getInventory().addItem(ItemHandler.createGem(args[0].toLowerCase()));
        player.sendMessage(ChatColor.GREEN+"You have been given a gem: "+ChatColor.translateAlternateColorCodes('&', JsonHandler.String_reader(args[0], "name")));
        return true;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!command.getLabel().equals("givegem")) return List.of();
        if (args.length > 0) {
            return ListHandler.GetBetterList(ListHandler.getGemList(), args, 0);
        } else return List.of();
    }


}
