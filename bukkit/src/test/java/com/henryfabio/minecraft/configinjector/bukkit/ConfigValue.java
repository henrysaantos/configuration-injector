package com.henryfabio.minecraft.configinjector.bukkit;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigSection;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.function.Function;

/**
 * @author Henry Fábio
 */
@Getter @Accessors(fluent = true)
@ConfigSection("mainSection")
@ConfigFile("config.yml")
public final class ConfigValue implements ConfigurationInjectable {

    @Getter private static final ConfigValue instance = new ConfigValue();

    @ConfigField("testValue") @TranslateColors private String testValue;
    @ConfigField("testValue2") private int testValue2;
    @ConfigField("testValue3") private boolean testValue3;
    @ConfigField("testValueSection1.testValue4") private String testValue4;
    @ConfigField("testValueSection1") private ConfigurationSection testValueSection1;
    @ConfigField("testList") @TranslateColors private List<String> testList;

    @ConfigSection("") @ConfigField("withoutSection") @TranslateColors private String withoutSection;

    public static <T> T get(Function<ConfigValue, T> function) {
        return function.apply(instance);
    }

}
