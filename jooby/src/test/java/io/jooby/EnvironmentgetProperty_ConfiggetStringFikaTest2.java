/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.typesafe.config.Config;

class EnvironmentgetProperty_ConfiggetStringFikaTest2 {

  @Test
  void test() {
    Config mockConfig = Mockito.mock(Config.class);
    Mockito.when(mockConfig.hasPath(Mockito.anyString())).thenReturn(true);
    Mockito.when(mockConfig.getString(Mockito.anyString())).thenReturn("testValue");

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    Environment environment = new Environment(classLoader, mockConfig, List.of("test"));

    environment.getProperty("anyKey");
  }
}
