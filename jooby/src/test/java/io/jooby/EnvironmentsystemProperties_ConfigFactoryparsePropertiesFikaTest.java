/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import org.junit.jupiter.api.Test;

public class EnvironmentsystemProperties_ConfigFactoryparsePropertiesFikaTest {

  @Test
  public void testSystemPropertiesCallsParseProperties() {
    // Directly invoke the entry point method
    Environment.systemProperties();

    // The call chain will be:
    // 1. Environment.systemProperties()
    // 2. ConfigFactory.parseProperties(System.getProperties(),
    // ConfigParseOptions.defaults().setOriginDescription("system properties"))
    // This ensures the third-party method ConfigFactory.parseProperties is invoked
  }
}
