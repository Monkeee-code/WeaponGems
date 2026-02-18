package me.monkeee.weaponGems.Commands;

import me.monkeee.weaponGems.Handlers.ItemHandler;
import me.monkeee.weaponGems.Handlers.JsonHandler;
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
        if (!(sender instanceof Player)) return false;
        if (!sender.isOp()) { sender.sendMessage(ChatColor.RED + "Only Operators can execute this command!"); return false; }
        ((Player) sender).getInventory().addItem(ItemHandler.createGem(args[0].toLowerCase()));
        sender.sendMessage(ChatColor.GREEN+"You have been given a gem: "+ChatColor.translateAlternateColorCodes('&', JsonHandler.String_reader(args[0], "name")));
        return true;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!command.getLabel().equals("givegem")) return List.of();
        if (args.length > 0) {
            return GetBetterList(getGemList(), args, 1);
        } else return List.of();
    }

    private static List<String> getGemList() {
        File file = new File(WeaponGems.getInstance().getDataFolder(), "items.json");
        try {
            String content = Files.readString(file.toPath());
            JSONObject json = new JSONObject(content);
            return new ArrayList<>(json.keySet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> GetBetterList(List<String> list, String[] args, int argStage) {
        List<String> completions = null;
        String input = args[argStage];
        for (String s : list) {
            if (s.toLowerCase().startsWith(input) || s.toUpperCase().startsWith(input)) {
                if (completions == null) {
                    completions = new ArrayList<>();
                }
                completions.add(s);
            }
        }
        if (completions != null) Collections.sort(completions);
        return completions;
    }
}
