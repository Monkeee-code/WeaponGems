package me.monkeee.weaponGems.API;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface GemTickingAbility {
    void onTick(Player player);
}
