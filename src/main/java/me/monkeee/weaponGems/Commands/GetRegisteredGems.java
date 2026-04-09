package me.monkeee.weaponGems.Commands;

import me.monkeee.weaponGems.API.GemDefinition;
import me.monkeee.weaponGems.API.GemRegistry;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class GetRegisteredGems implements CommandExecutor {

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String @NonNull [] args) {
        if (!(sender instanceof Player player)) return false;
        if (!(player.isOp() || player.hasPermission("wg.getall"))) return false;
        player.sendMessage(ChatColor.YELLOW+"[?] Here are all of the registered gems on the server:");
        for (GemDefinition gem : GemRegistry.getAll()) {
            TextComponent gemMessage = new TextComponent(ChatColor.translateAlternateColorCodes('&', gem.getDisplayName()));

            ComponentBuilder hoverText = new ComponentBuilder("=== Gem's Info ===").color(ChatColor.RED.asBungee()).bold(true)
                    .append(ChatColor.GREEN.asBungee()+"\nTag: "+gem.getID()).color(ChatColor.YELLOW.asBungee()).bold(false)
                    .append(ChatColor.AQUA.asBungee()+"\nDisplay Name: "+ChatColor.translateAlternateColorCodes('&', gem.getDisplayName())).color(ChatColor.WHITE.asBungee())
                    .append(ChatColor.GREEN.asBungee()+"\nMaterial: "+ChatColor.YELLOW.asBungee()+gem.getMaterial().toString())
                    .append(ChatColor.AQUA.asBungee()+"\nSpawn Chance: "+ChatColor.YELLOW.asBungee()+gem.getSpawnChance()+" ("+ChatColor.RED.asBungee()+gem.getSpawnChance()*100+ChatColor.YELLOW.asBungee()+"%)")
                    .append(ChatColor.GREEN.asBungee()+"\nLootTables: "+ChatColor.LIGHT_PURPLE.asBungee()+gem.getLootTables().toString())
                    .append(ChatColor.AQUA.asBungee()+"\nApplicableItems: "+ChatColor.GREEN.asBungee()+gem.getApplicableItems().toString());

            gemMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(hoverText.create())));

            player.spigot().sendMessage(gemMessage);
        }
        return true;
    }
}
