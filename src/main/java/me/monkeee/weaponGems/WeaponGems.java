package me.monkeee.weaponGems;

import de.tr7zw.changeme.nbtapi.NBT;
import me.monkeee.weaponGems.Abilities.CooldownNotification;
import me.monkeee.weaponGems.Abilities.TickingAbility;
import me.monkeee.weaponGems.Commands.*;
import me.monkeee.weaponGems.Events.*;
import me.monkeee.weaponGems.Handlers.GemRegistrar;
import me.monkeee.weaponGems.Handlers.GemItemHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class WeaponGems extends JavaPlugin {

    private static WeaponGems instance;

    @Override
    public void onEnable() {
        instance = this;

        if (!NBT.preloadApi()) {
            getLogger().warning("NBT-API wasn't initialized properly, disabling the plugin.");
            getPluginLoader().disablePlugin(this);
            return;
        }

        saveDefaultConfig();
        saveResource("items.json", getConfig().getBoolean("replace-json"));

        // 1. Register all built-in gems from items.json into GemRegistry
        GemRegistrar.registerAll(getDataFolder());
        getLogger().info("Built-in gems registered: " + me.monkeee.weaponGems.API.GemRegistry.getAllIDs());

        // 2. Addon plugins register their gems here via GemRegistry.register(...)
        //    (they depend on this plugin and call register in their own onEnable,
        //     which runs after this plugin is fully enabled)

        // 3. Rebuild the legacy MapOfItems compat shim so ApplyCommand still works
        GemItemHandler.ApplyItemsToList();

        // 4. Start systems
        TickingAbility.startTicking();
        CooldownNotification.startNotificationTimer();

        // 5. Commands
        Objects.requireNonNull(getCommand("wgapply")).setTabCompleter(new ApplyCommand());
        Objects.requireNonNull(getCommand("wgapply")).setExecutor(new ApplyCommand());
        Objects.requireNonNull(getCommand("wgreload")).setExecutor(new WGReload());
        Objects.requireNonNull(getCommand("givegem")).setExecutor(new GiveGemstone());
        Objects.requireNonNull(getCommand("givegem")).setTabCompleter(new GiveGemstone());
        Objects.requireNonNull(getCommand("wgcds")).setExecutor(new GetCooldownCommand());
        Objects.requireNonNull(getCommand("wgremove")).setExecutor(new RemoveGemCommand());
        Objects.requireNonNull(getCommand("wgremove")).setTabCompleter(new RemoveGemCommand());

        // 6. Events
        getServer().getPluginManager().registerEvents(new EntityDamageEntityEvent(), this);
        getServer().getPluginManager().registerEvents(new LootTableGeneration(), this);
        getServer().getPluginManager().registerEvents(new OnEntityDamageEvent(), this);
        getServer().getPluginManager().registerEvents(new PreventItemInteractionEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerConnectEvent(), this);
    }

    @Override
    public void onDisable() {
        me.monkeee.weaponGems.API.GemRegistry.clear();
    }

    public static WeaponGems getInstance() {
        return instance;
    }
}