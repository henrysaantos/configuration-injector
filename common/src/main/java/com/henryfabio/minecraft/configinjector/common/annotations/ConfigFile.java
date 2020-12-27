package com.henryfabio.minecraft.configinjector.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Henry Fábio
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface ConfigFile {

    /**
     * File path to search configuration values
     *
     * @return file path
     */
    String value();

}
