package com.henryfabio.minecraft.configinjector.bungee;

import com.henryfabio.minecraft.configinjector.bungee.injector.BungeeConfigurationInjector;
import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author Henry Fábio
 */
public class Main extends Plugin {

    @Override
    public void onEnable() {
        BungeeConfigurationInjector configurationInjector = new BungeeConfigurationInjector(this);
        configurationInjector.saveDefaultConfiguration(this, "config.yml");

        configurationInjector.injectConfiguration(ConfigValue.instance());

        System.out.println("testValue: '" + ConfigValue.get(ConfigValue::testValue) + "'");
        System.out.println("testValue2: '" + ConfigValue.get(ConfigValue::testValue2) + "'");
        System.out.println("testValue3: '" + ConfigValue.get(ConfigValue::testValue3) + "'");
        System.out.println("testValue4: '" + ConfigValue.get(ConfigValue::testValue4) + "'");
        System.out.println("testList: '" + ConfigValue.get(ConfigValue::testList) + "'");
    }

}
