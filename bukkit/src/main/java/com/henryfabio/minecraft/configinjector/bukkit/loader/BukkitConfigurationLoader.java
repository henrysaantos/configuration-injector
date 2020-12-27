package com.henryfabio.minecraft.configinjector.bukkit.loader;

import com.henryfabio.minecraft.configinjector.bukkit.configuration.BukkitConfiguration;
import com.henryfabio.minecraft.configinjector.common.configuration.Configuration;
import com.henryfabio.minecraft.configinjector.common.loader.ConfigurationLoader;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * @author Henry Fábio
 */
public final class BukkitConfigurationLoader extends ConfigurationLoader {

    @Override
    public Configuration loadConfiguration(File dataFolder, String path) {
        return computeConfigurationIfAbsent(path, () -> {
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(new File(dataFolder, path));
            return new BukkitConfiguration(path, configuration);
        });
    }

}
