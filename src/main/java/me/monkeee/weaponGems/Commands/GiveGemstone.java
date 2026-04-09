package me.monkeee.weaponGems.Commands;

import me.monkeee.weaponGems.API.GemDefinition;
import me.monkeee.weaponGems.API.GemRegistry;
import me.monkeee.weaponGems.GemID;
import me.monkeee.weaponGems.Handlers.ItemHandler;
import me.monkeee.weaponGems.Handlers.JsonHandler;
import me.monkeee.weaponGems.Handlers.ListHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;
import java.util.Objects;

public class GiveGemstone implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!(sender instanceof Player player)) return false;
        if (!player.isOp()) { player.sendMessage(ChatColor.RED + "[!] Only Operators can execute this command!"); return false; }
        if (args[0].isEmpty()) { player.sendMessage(ChatColor.RED + "[!] Please, mention a gem to use!"); return false; }
        List<String> gemList = ListHandler.getGemList();
        gemList.add("all");
        if (!gemList.contains(args[0])) { player.sendMessage(ChatColor.RED+"Gem \""+args[0]+"\" does not exist!"); return false; }
        if (Objects.equals(args[0], "all")) {
            for (GemDefinition gem : GemRegistry.getAll()) {
                player.getInventory().addItem(ItemHandler.createGem(gem.getID()));
                player.sendMessage(ChatColor.GREEN+"[!] You have been given a gem: "+ChatColor.translateAlternateColorCodes('&', gem.getDisplayName()));
            }
        } else {
            player.getInventory().addItem(ItemHandler.createGem(args[0]));
            player.sendMessage(ChatColor.GREEN + "[!] You have been given a gem: " + ChatColor.translateAlternateColorCodes('&', GemRegistry.get(args[0]).get().getDisplayName()));
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!command.getLabel().equals("givegem")) return List.of();
        if (args.length > 0) {
            List<String> list = ListHandler.getGemList();
            list.add("all");
            return ListHandler.GetBetterList(list, args, 0);
        } else return List.of();
    }


}
