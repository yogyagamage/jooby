/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;

public class EnvironmentsystemProperties_ConfigParseOptionssetOriginDescriptionFikaTest {

  @Test
  public void testSystemPropertiesCallsSetOriginDescription() {
    Config config = Environment.systemProperties();
  }
}
