package com.henryfabio.minecraft.configinjector.bungee.loader;

import com.henryfabio.minecraft.configinjector.bungee.configuration.BungeeConfiguration;
import com.henryfabio.minecraft.configinjector.common.configuration.Configuration;
import com.henryfabio.minecraft.configinjector.common.loader.ConfigurationLoader;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * @author Henry Fábio
 */
public final class BungeeConfigurationLoader extends ConfigurationLoader {

    @Override
    public Configuration loadConfiguration(File dataFolder, String path) {
        return computeConfigurationIfAbsent(path, () -> {
            try {
                ConfigurationProvider provider = ConfigurationProvider.getProvider(YamlConfiguration.class);
                return new BungeeConfiguration(path, provider.load(new File(dataFolder, path)));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
    }

}
