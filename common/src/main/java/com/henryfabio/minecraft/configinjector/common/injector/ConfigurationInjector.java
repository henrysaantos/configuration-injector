package com.henryfabio.minecraft.configinjector.common.injector;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.configuration.Configuration;
import com.henryfabio.minecraft.configinjector.common.loader.ConfigurationLoader;
import com.henryfabio.minecraft.configinjector.common.model.ConfigurationField;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Henry Fábio
 */
@Getter
@RequiredArgsConstructor
public abstract class ConfigurationInjector {

    private final ConfigurationLoader configurationLoader;
    private final File dataFolder;

    public <T extends ConfigurationInjectable> T injectConfiguration(T injectable) {
        Class<?> injectableClass = injectable.getClass();

        ConfigFile configFile = injectableClass.getAnnotation(ConfigFile.class);
        if (configFile == null) {
            throw new UnsupportedOperationException("The class must be annotated with ConfigValue");
        }

        TranslateColors fileTranslateColors = injectableClass.getAnnotation(TranslateColors.class);

        Configuration configuration = configurationLoader.loadConfiguration(dataFolder, configFile.value());
        for (ConfigurationField configurationField : getConfigurationFields(injectable)) {
            configurationField.accessible();

            Object configurationValue = getConfigurationValue(configuration, configurationField, fileTranslateColors);
            injectValue(injectable, configurationField, configurationValue);
        }

        return injectable;
    }

    private void injectValue(ConfigurationInjectable injectable, ConfigurationField configurationField, Object value) {
        try {
            Field holder = configurationField.getHolder();
            holder.set(injectable, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Object getConfigurationValue(Configuration configuration,
                                         ConfigurationField configurationField,
                                         TranslateColors fileTranslateColors) {
        String fieldConfigurationPath = configurationField.getConfigurationPath();
        if (fieldConfigurationPath != null) {
            configuration = configurationLoader.loadConfiguration(dataFolder, fieldConfigurationPath);
        }

        Object configurationValue = configuration.get(configurationField.getFieldAnnotation().value());

        TranslateColors translateColors = configurationField.getTranslateColors();
        if (translateColors == null) translateColors = fileTranslateColors;

        if (translateColors != null && translateColors.value()) {
            configurationValue = translateValueColors(translateColors, configurationValue);
        }

        return configurationValue;
    }

    private Object translateValueColors(TranslateColors translateColors, Object value) {
        if (value instanceof String) {
            return translateColors(translateColors.colorChar(), (String) value);
        } else if (value instanceof List) {
            return ((List<String>) value).stream()
                    .map(it -> translateColors(translateColors.colorChar(), it))
                    .collect(Collectors.toList());
        }
        return value;
    }

    private Set<ConfigurationField> getConfigurationFields(ConfigurationInjectable injectable) {
        return Arrays.stream(injectable.getClass().getDeclaredFields())
                .map(ConfigurationField::new)
                .filter(it -> it.getFieldAnnotation() != null)
                .collect(Collectors.toSet());
    }

    protected abstract String translateColors(char symbol, String message);

}
