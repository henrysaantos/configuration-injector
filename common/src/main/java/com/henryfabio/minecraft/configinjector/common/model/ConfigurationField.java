package com.henryfabio.minecraft.configinjector.common.model;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigSection;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Henry Fábio
 */
@Data
public final class ConfigurationField {

    private final Field holder;

    private final ConfigFile fileAnnotation;
    private final ConfigField fieldAnnotation;
    private final ConfigSection sectionAnnotation;
    private final TranslateColors translateColors;

    public ConfigurationField(Field holder) {
        this.holder = holder;
        this.fileAnnotation = holder.getAnnotation(ConfigFile.class);
        this.fieldAnnotation = holder.getAnnotation(ConfigField.class);
        this.sectionAnnotation = holder.getAnnotation(ConfigSection.class);
        this.translateColors = holder.getAnnotation(TranslateColors.class);
    }

    public String getConfigurationPath() {
        return fileAnnotation != null ? fileAnnotation.value() : null;
    }

    public void accessible() {
        holder.setAccessible(true);
        try {
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);

            modifiersField.setInt(holder, holder.getModifiers() & ~Modifier.PRIVATE);
        } catch (NoSuchFieldException | IllegalAccessException ignore) {
        }
    }

}
