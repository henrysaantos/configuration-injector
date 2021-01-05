package com.henryfabio.minecraft.configinjector.bungee.injector;

import com.henryfabio.minecraft.configinjector.bungee.loader.BungeeConfigurationLoader;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjector;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @author Henry Fábio
 */
public final class BungeeConfigurationInjector extends ConfigurationInjector {

    public BungeeConfigurationInjector(Plugin plugin) {
        super(new BungeeConfigurationLoader(), plugin.getDataFolder());
    }

    public void saveDefaultConfiguration(Plugin plugin, String path) {
        File dataFolder = plugin.getDataFolder();

        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        File file = new File(dataFolder, path);
        if (!file.exists()) {
            try (InputStream in = plugin.getResourceAsStream(path)) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
