package com.henryfabio.minecraft.configinjector.common.injector;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigSection;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.configuration.Configuration;
import com.henryfabio.minecraft.configinjector.common.loader.ConfigurationLoader;
import com.henryfabio.minecraft.configinjector.common.model.ConfigurationField;
import com.henryfabio.minecraft.configinjector.common.model.ConfigurationFile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
        ConfigurationFile configurationFile = new ConfigurationFile(injectable);

        ConfigFile fileAnnotation = configurationFile.getFileAnnotation();
        if (fileAnnotation == null) {
            throw new UnsupportedOperationException("The class must be annotated with ConfigFile");
        }

        Configuration configuration = configurationLoader.loadConfiguration(dataFolder, fileAnnotation.value());
        for (ConfigurationField configurationField : getConfigurationFields(injectable)) {
            configurationField.accessible();

            Object configurationValue = getConfigurationValue(configuration, configurationField, configurationFile);
            injectValue(injectable, configurationField, configurationValue);
        }

        return injectable;
    }

    @SafeVarargs
    public final <T extends ConfigurationInjectable> void injectConfiguration(T... injectables) {
        for (T injectable : injectables) {
            injectConfiguration(injectable);
        }
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
                                         ConfigurationFile configurationFile) {
        String fieldConfigurationPath = configurationField.getConfigurationPath();
        if (fieldConfigurationPath != null) {
            configuration = configurationLoader.loadConfiguration(dataFolder, fieldConfigurationPath);
        }

        ConfigSection sectionAnnotation = Optional.ofNullable(configurationField.getSectionAnnotation())
                .orElse(configurationFile.getSectionAnnotation());
        TranslateColors translateColors = Optional.ofNullable(configurationField.getTranslateColors())
                .orElse(configurationFile.getTranslateColors());

        String extraSection = Optional.ofNullable(sectionAnnotation)
                .filter(annotation -> !annotation.value().isEmpty())
                .map(annotation -> annotation.value() + ".")
                .orElse("");

        Object configurationValue = configuration.get(extraSection + configurationField.getFieldAnnotation().value());

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
