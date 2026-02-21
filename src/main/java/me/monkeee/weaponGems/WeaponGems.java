package me.monkeee.weaponGems;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.Abilities.TickingAbility;
import me.monkeee.weaponGems.Commands.ApplyCommand;
import me.monkeee.weaponGems.Commands.GiveGemstone;
import me.monkeee.weaponGems.Commands.WGReload;
import me.monkeee.weaponGems.Events.EntityDamageEntityEvent;
import me.monkeee.weaponGems.Events.LootTableGeneration;
import me.monkeee.weaponGems.Handlers.GemItemHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class WeaponGems extends JavaPlugin {

    private static WeaponGems instance;

    @Override
    public void onEnable() {
        instance = this;

        if (!NBT.preloadApi()) {
            getLogger().warning("NBT-API wasn't initialized properly, disabling the plugin");
            getPluginLoader().disablePlugin(this);
            return;
        }
        saveDefaultConfig();

        boolean replaceJSON = getConfig().getBoolean("replace-json");
        saveResource("items.json", replaceJSON);

        TickingAbility.startTicking();
        GemItemHandler.ApplyItemsToList();
        Objects.requireNonNull(getCommand("wgapply")).setTabCompleter(new ApplyCommand());
        Objects.requireNonNull(getCommand("wgapply")).setExecutor(new ApplyCommand());
        Objects.requireNonNull(getCommand("wgreload")).setExecutor(new WGReload());
        Objects.requireNonNull(getCommand("givegem")).setExecutor(new GiveGemstone());
        Objects.requireNonNull(getCommand("givegem")).setTabCompleter(new GiveGemstone());
        getServer().getPluginManager().registerEvents(new EntityDamageEntityEvent(), this);
        getServer().getPluginManager().registerEvents(new LootTableGeneration(), this);
    }

    public static WeaponGems getInstance() {
        return instance;
    }

}
