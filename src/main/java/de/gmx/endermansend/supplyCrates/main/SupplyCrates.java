package de.gmx.endermansend.supplyCrates.main;

import de.gmx.endermansend.supplyCrates.chest.SpawnCoordinator;
import de.gmx.endermansend.supplyCrates.config.ConfigHandler;
import de.gmx.endermansend.supplyCrates.listeners.BlockBreakListener;
import de.gmx.endermansend.supplyCrates.listeners.EmptyChestListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SupplyCrates extends JavaPlugin {

    private static SupplyCrates instance;

    private ConfigHandler config;

    @Override
    public void onEnable() {

        instance = this;

        config = new ConfigHandler(this);

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new EmptyChestListener(), this);
        pluginManager.registerEvents(new BlockBreakListener(), this);

        int interval = config.get.spawnInterval();

        (new SpawnCoordinator()).runTaskTimer(this, interval, interval);

        getLogger().info("Enabled");

    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled");
    }

    public ConfigHandler getConfigHandler() {
        return config;
    }

    public static SupplyCrates getInstance() {
        return instance;
    }

}
