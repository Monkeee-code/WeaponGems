package me.monkeee.weaponGems.Commands;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.Handlers.GemItemHandler;
import me.monkeee.weaponGems.Handlers.JsonHandler;
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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplyCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!(sender instanceof Player player)) return false;
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED+"Are you sure you want to do this?");
            player.sendMessage(ChatColor.RED+"When a gem is "+ChatColor.YELLOW+"applied"+ChatColor.RED+", it won't be able to be removed!");
            player.sendMessage(ChatColor.GOLD+"If you are sure, type "+ChatColor.GREEN+"/wgapply confirm");
            return false;
        }

        ItemStack MainHand = player.getInventory().getItemInMainHand();
        ItemStack OffHand = player.getInventory().getItemInOffHand();
        if (MainHand.getType().equals(Material.valueOf("AIR")) || OffHand.getType().equals(Material.valueOf("AIR"))) {
            player.sendMessage(ChatColor.RED+"Please, use a valid item!");
            return false;
        }
        boolean isGem = NBT.get(OffHand, nbt -> {
           return nbt.getBoolean("isGem");
        });
        if (!isGem) {
            player.sendMessage(ChatColor.RED+"The item in offhand is not a gem!");
            return false;
        }


        if (args[0].equalsIgnoreCase("confirm")) ApplyGem(MainHand, OffHand, player);
        return true;
    }

    private static void ApplyGem(ItemStack mainHand, ItemStack offHand, Player player) {
        String GemType = getGemType(offHand);
        ItemMeta mainHandMeta = mainHand.getItemMeta();
        if (mainHandMeta == null) return;
        List<String> lore = mainHandMeta.getLore() != null ? new ArrayList<>(mainHandMeta.getLore()) : new ArrayList<>();
        if (isAlreadyApplied(mainHand, GemType)) {
            player.sendMessage(ChatColor.RED+"The item already has this type of gem!");
            return;
        }


        if (GemType.equalsIgnoreCase("deflection_eye")) {
            if (isCorrectItem(mainHand, GemType)) {
                NBT.modify(mainHand, nbt -> {
                    lore.add(ChatColor.translateAlternateColorCodes('&', JsonHandler.String_reader(GemType, "name")));
                    mainHandMeta.setLore(lore);
                    mainHand.setItemMeta(mainHandMeta);
                    nbt.setBoolean("deflection_eye", true);
                });
                player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
            } else {
                player.sendMessage(ChatColor.RED+"The item is not valid for this gem!");
                return;
            }
        }
        if (GemType.equalsIgnoreCase("jade")) {
            if (isCorrectItem(mainHand, GemType)) {
                NBT.modify(mainHand, nbt -> {
                    lore.add(ChatColor.translateAlternateColorCodes('&', JsonHandler.String_reader(GemType, "name")));
                    mainHandMeta.setLore(lore);
                    mainHand.setItemMeta(mainHandMeta);
                    nbt.setBoolean("jade", true);
                });
                player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
            }else {
                player.sendMessage(ChatColor.RED+"The item is not valid for this gem!");
                return;
            }
        }
        if (GemType.equalsIgnoreCase("divan_core")) {
            if (isCorrectItem(mainHand, GemType)) {
                NBT.modify(mainHand, nbt -> {
                    lore.add(ChatColor.translateAlternateColorCodes('&', JsonHandler.String_reader(GemType, "name")));
                    mainHandMeta.setLore(lore);
                    mainHand.setItemMeta(mainHandMeta);
                    nbt.setBoolean("divan_core", true);
                });
                player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
            }else {
                player.sendMessage(ChatColor.RED+"The item is not valid for this gem!");
                return;
            }
        }
        if (GemType.equalsIgnoreCase("darkstone")) {
            if (isCorrectItem(mainHand, GemType)) {
                NBT.modify(mainHand, nbt -> {
                    lore.add(ChatColor.translateAlternateColorCodes('&', JsonHandler.String_reader(GemType, "name")));
                    mainHandMeta.setLore(lore);
                    mainHand.setItemMeta(mainHandMeta);
                    nbt.setBoolean("darkstone", true);
                });
                player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
            }else {
                player.sendMessage(ChatColor.RED+"The item is not valid for this gem!");
                return;
            }
        }
        if (GemType.equalsIgnoreCase("ruby")) {
            if (isCorrectItem(mainHand, GemType)) {
                NBT.modify(mainHand, nbt -> {
                    lore.add(ChatColor.translateAlternateColorCodes('&', JsonHandler.String_reader(GemType, "name")));
                    mainHandMeta.setLore(lore);
                    mainHand.setItemMeta(mainHandMeta);
                    nbt.setBoolean("ruby", true);
                });
                player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
            }else {
                player.sendMessage(ChatColor.RED+"The item is not valid for this gem!");
                return;
            }
        }
        if (GemType.equalsIgnoreCase("spider_fang")) {
            if (isCorrectItem(mainHand, GemType)) {
                NBT.modify(mainHand, nbt -> {
                    lore.add(ChatColor.translateAlternateColorCodes('&', JsonHandler.String_reader(GemType, "name")));
                    mainHandMeta.setLore(lore);
                    mainHand.setItemMeta(mainHandMeta);
                    nbt.setBoolean("spider_fang", true);
                });
                player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
            }else {
                player.sendMessage(ChatColor.RED+"The item is not valid for this gem!");
                return;
            }
        }

        player.sendMessage(ChatColor.YELLOW+GemType+ChatColor.GREEN+" has been applied to: "+ ChatColor.RESET+ mainHand.getType());
    }

    private static String getGemType(ItemStack item) {
        return NBT.get(item, nbt -> {
           return nbt.getString("gemType");
        });
    }

    private static boolean isCorrectItem(ItemStack item, String gem) {
        Pattern PREFIX = Pattern.compile("^[a-z]+_");
        Matcher matcher = PREFIX.matcher(item.getType().toString().toLowerCase());
        if (gem.equalsIgnoreCase("deflection_eye")) {
            return GemItemHandler.DeflectionEyeItems.contains(matcher.replaceFirst(""));
        } else if (gem.equalsIgnoreCase("jade")) {
            return GemItemHandler.JadeItems.contains(matcher.replaceFirst(""));
        } else if (gem.equalsIgnoreCase("divan_core")) {
            return GemItemHandler.DivanCoreItems.contains(matcher.replaceFirst(""));
        } else if (gem.equalsIgnoreCase("darkstone")) {
            return GemItemHandler.DarkstoneItems.contains(matcher.replaceFirst(""));
        } else if (gem.equalsIgnoreCase("ruby")) {
            return GemItemHandler.RubyItems.contains(matcher.replaceFirst(""));
        } else if (gem.equalsIgnoreCase("spider_fang")) {
            return GemItemHandler.SpiderFangItems.contains(matcher.replaceFirst(""));
        }
        return false;
    }

    private static boolean isAlreadyApplied(ItemStack item, String gem) {
        boolean hasDarkstone = NBT.get(item, nbt -> (boolean) nbt.getBoolean(gem));
        boolean hasDeflectionEye = NBT.get(item, nbt -> (boolean) nbt.getBoolean(gem));
        boolean hasDivanCore = NBT.get(item, nbt -> (boolean) nbt.getBoolean(gem));
        boolean hasJade = NBT.get(item, nbt -> (boolean) nbt.getBoolean(gem));
        boolean hasRuby = NBT.get(item, nbt -> (boolean) nbt.getBoolean(gem));
        boolean hasSpiderFang = NBT.get(item, nbt -> (boolean) nbt.getBoolean(gem));

        return hasDarkstone && hasDeflectionEye && hasDivanCore && hasJade && hasRuby && hasSpiderFang;
    }

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String[] args) {
        List<String> output = new ArrayList<>();
        output.add("confirm");

        if (args.length > 0) {
            return GiveGemstone.GetBetterList(output, args, 0);
        }

        return List.of();
    }
}
