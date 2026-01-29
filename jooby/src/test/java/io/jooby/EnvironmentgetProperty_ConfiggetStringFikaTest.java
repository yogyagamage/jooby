/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;

class EnvironmentgetProperty_ConfiggetStringFikaTest {

  @Test
  void testGetPropertyCallsConfigGetString() {
    Config mockConfig = mock(Config.class);
    when(mockConfig.hasPath(anyString())).thenReturn(true);
    when(mockConfig.getString(anyString())).thenReturn("value");

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    Environment environment = new Environment(classLoader, mockConfig, "test");

    environment.getProperty("some.key", "default");
  }
}
