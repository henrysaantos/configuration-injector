package com.henryfabio.minecraft.configinjector.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Henry Fábio
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ConfigField {

    /**
     * Configuration section path to return value
     *
     * @return configuration section
     */
    String value();

    /**
     * If colorize is true, the value has the colors translated
     *
     * @return colorize option
     */
    boolean colorize() default false;

}
