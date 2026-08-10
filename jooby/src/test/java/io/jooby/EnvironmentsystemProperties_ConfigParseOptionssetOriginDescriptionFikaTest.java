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

public class EnvironmentsystemProperties_ConfigParseOptionssetOriginDescriptionFikaTest {

  @Test
  public void testSystemPropertiesTriggersSetOriginDescription() {
    Config mockConfig = Mockito.mock(Config.class);
    ClassLoader mockClassLoader = Mockito.mock(ClassLoader.class);

    Environment environment = new Environment(mockClassLoader, mockConfig, List.of("test"));

    Environment.systemProperties();
  }
}
