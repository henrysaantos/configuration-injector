package com.henryfabio.minecraft.configinjector.bukkit.configuration;

import com.henryfabio.minecraft.configinjector.common.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author Henry Fábio
 */
public final class BukkitConfiguration extends Configuration {

    private final FileConfiguration fileConfiguration;

    public BukkitConfiguration(String path, FileConfiguration fileConfiguration) {
        super(path);
        this.fileConfiguration = fileConfiguration;
    }

    @Override
    public Object get(String section) {
        return fileConfiguration.get(section);
    }

}
