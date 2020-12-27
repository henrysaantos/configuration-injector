package com.henryfabio.minecraft.configinjector.bungee.configuration;

import com.henryfabio.minecraft.configinjector.common.configuration.Configuration;

/**
 * @author Henry Fábio
 */
public final class BungeeConfiguration extends Configuration {

    private final net.md_5.bungee.config.Configuration configuration;

    public BungeeConfiguration(String path, net.md_5.bungee.config.Configuration configuration) {
        super(path);
        this.configuration = configuration;
    }

    @Override
    public Object get(String section) {
        return configuration.get(section);
    }

}
