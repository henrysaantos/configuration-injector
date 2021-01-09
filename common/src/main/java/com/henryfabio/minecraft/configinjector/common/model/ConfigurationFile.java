package com.henryfabio.minecraft.configinjector.common.model;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigSection;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.Data;

@Data
public class ConfigurationFile {

    private final ConfigurationInjectable holder;

    private final ConfigFile fileAnnotation;
    private final ConfigSection sectionAnnotation;
    private final TranslateColors translateColors;

    public ConfigurationFile(ConfigurationInjectable holder) {
        this.holder = holder;
        Class<? extends ConfigurationInjectable> holderClass = holder.getClass();
        this.fileAnnotation = holderClass.getAnnotation(ConfigFile.class);
        this.sectionAnnotation = holderClass.getAnnotation(ConfigSection.class);
        this.translateColors = holderClass.getAnnotation(TranslateColors.class);
    }

}
