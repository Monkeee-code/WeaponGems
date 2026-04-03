package me.monkeee.weaponGems.Events;

import me.monkeee.weaponGems.Handlers.CooldownHandler;
import me.monkeee.weaponGems.WeaponGems;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerConnectEvent implements Listener {

    public static List<UUID> leftPlayers = new ArrayList<>();

    @EventHandler
    public static void onLeave(PlayerQuitEvent event) {
        leftPlayers.add(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public static void onJoin(PlayerJoinEvent event) {
        leftPlayers.remove(event.getPlayer().getUniqueId());
    }

    public static void removeLeftPlayers() {
        Bukkit.getScheduler().runTaskTimer(WeaponGems.getInstance(), () -> {
            for (UUID player : leftPlayers) {
                CooldownHandler.removePlayer(player);
                leftPlayers.remove(player);
            }
        }, 0L, 1200L);
    }
}
