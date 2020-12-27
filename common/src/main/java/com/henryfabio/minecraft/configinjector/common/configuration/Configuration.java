package com.henryfabio.minecraft.configinjector.common.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Henry Fábio
 */
@Getter
@RequiredArgsConstructor
public abstract class Configuration {

    private final String path;

    abstract public Object get(String section);

}
