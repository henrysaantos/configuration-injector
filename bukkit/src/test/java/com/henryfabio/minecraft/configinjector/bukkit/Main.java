package com.henryfabio.minecraft.configinjector.bukkit;

import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Henry Fábio
 */
public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        BukkitConfigurationInjector configurationInjector = new BukkitConfigurationInjector(this);
        configurationInjector.injectConfiguration(ConfigValue.instance());

        System.out.println("testValue: '" + ConfigValue.get(ConfigValue::testValue) + "'");
        System.out.println("testValue2: '" + ConfigValue.get(ConfigValue::testValue2) + "'");
        System.out.println("testValue3: '" + ConfigValue.get(ConfigValue::testValue3) + "'");
        System.out.println("testValue4: '" + ConfigValue.get(ConfigValue::testValue4) + "'");
        System.out.println("testSection1: '" + ConfigValue.get(ConfigValue::testValueSection1) + "'");
        System.out.println("testList: '" + ConfigValue.get(ConfigValue::testList) + "'");
        System.out.println("withoutSection: '" + ConfigValue.get(ConfigValue::withoutSection) + "'");
    }

}
