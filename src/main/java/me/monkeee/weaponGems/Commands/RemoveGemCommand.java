package me.monkeee.weaponGems.Commands;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.GemID;
import me.monkeee.weaponGems.Handlers.ItemHandler;
import me.monkeee.weaponGems.Handlers.JsonHandler;
import me.monkeee.weaponGems.Handlers.ListHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;
import java.util.Objects;

public class RemoveGemCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {
        if (!(sender instanceof Player player)) return false;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED+"[!] Please, specify a gem to remove!");
            return false;
        } else if (args.length == 1) {
            player.sendMessage(ChatColor.RED+"[?] Are you want to do this?");
            player.sendMessage(ChatColor.YELLOW+"[?] There is a "+ChatColor.RED+"50% chance"+ChatColor.YELLOW+" for the gem to be DESTROYED!");
            player.sendMessage(ChatColor.GRAY+"[?] If you are sure, please type:"+ChatColor.GREEN+" /wgremove "+args[0]+" confirm");
            return false;
        }
        if (!ListHandler.getGemList().contains(args[0])) {
            player.sendMessage(ChatColor.RED+"[!] There exist no gems by that name!");
            return false;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        GemID gem = GemID.valueOf(args[0]);
        if (item.getType() == Material.AIR) return false;
        removeGem(item, player, gem);
        return true;
    }

    private static void removeGem(ItemStack item, Player player, GemID gem) {
        if (!hasGem(gem, item)) {
            player.sendMessage(ChatColor.RED+"[!] Item does not have the specified gem!");
            return;
        }

        ItemMeta itemMeta = item.getItemMeta();
        List<String> itemLore = Objects.requireNonNull(item.getItemMeta()).getLore();
        assert itemMeta != null;
        assert itemLore != null;
        if (isDestroyed()) {
            Objects.requireNonNull(itemLore).remove(ChatColor.translateAlternateColorCodes('&', JsonHandler.String_reader(gem.toString(), "name")));
            itemMeta.setLore(itemLore);
            item.setItemMeta(itemMeta);

            NBT.modify(item, nbt -> {
                nbt.removeKey(gem.toString());
            });
            player.sendMessage(ChatColor.YELLOW+gem.toString()+ChatColor.GREEN+" has been removed from "+ChatColor.WHITE+item.getType());
            player.sendMessage(ChatColor.RED+"[!] The gem has unfortunately been Destroyed!");
            return;
        }
        Objects.requireNonNull(itemLore).remove(ChatColor.translateAlternateColorCodes('&', JsonHandler.String_reader(gem.toString(), "name")));
        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);

        NBT.modify(item, nbt -> {
            nbt.removeKey(gem.toString());
        });
        player.sendMessage(ChatColor.YELLOW+gem.toString()+ChatColor.GREEN+" has been removed from "+ChatColor.WHITE+item.getType());
        player.getInventory().addItem(ItemHandler.createGem(gem));
    }

    private static boolean hasGem(GemID gem, ItemStack item) {
        return NBT.get(item, nbt -> (boolean) nbt.getBoolean(gem.toString()));
    }

    private static boolean isDestroyed() {
        double rand = Math.random();
        double percent = 0.5;
        return rand > percent;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {
        List<String> confirm = List.of("confirm");
        if (args.length == 1) return ListHandler.GetBetterList(ListHandler.getGemList(), args, 0);
        if (args.length > 1) return ListHandler.GetBetterList(confirm, args, 1);

        return List.of();
    }
}
