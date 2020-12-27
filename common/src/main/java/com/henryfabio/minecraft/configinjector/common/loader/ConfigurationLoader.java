package com.henryfabio.minecraft.configinjector.common.loader;

import com.henryfabio.minecraft.configinjector.common.configuration.Configuration;
import lombok.Getter;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Henry Fábio
 */
@Getter
public abstract class ConfigurationLoader {

    private final Map<String, Configuration> configurations = new LinkedHashMap<>();

    public Configuration computeConfigurationIfAbsent(String name, Supplier<Configuration> supplier) {
        return configurations.computeIfAbsent(name, k -> supplier.get());
    }

    public abstract Configuration loadConfiguration(File dataFolder, String path);

}
