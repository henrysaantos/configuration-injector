package com.henryfabio.minecraft.configinjector.bukkit.injector;

import com.henryfabio.minecraft.configinjector.bukkit.loader.BukkitConfigurationLoader;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjector;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

/**
 * @author Henry Fábio
 */
public final class BukkitConfigurationInjector extends ConfigurationInjector {

    public BukkitConfigurationInjector(Plugin plugin) {
        super(new BukkitConfigurationLoader(), plugin.getDataFolder());
    }

    public void saveDefaultConfiguration(Plugin plugin, String path) {
        plugin.saveResource(path, false);
    }

    public void saveDefaultConfiguration(Plugin plugin, String... paths) {
        for (String path : paths) {
            saveDefaultConfiguration(plugin, path);
        }
    }

    @Override
    protected String translateColors(char symbol, String message) {
        return ChatColor.translateAlternateColorCodes(symbol, message);
    }

}
