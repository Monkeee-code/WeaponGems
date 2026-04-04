package me.monkeee.weaponGems.ItemGems;

import org.bukkit.entity.Player;

public interface GemAbility {
    void ability(Player player1, Player player2);
    String getAbilityName();
}
