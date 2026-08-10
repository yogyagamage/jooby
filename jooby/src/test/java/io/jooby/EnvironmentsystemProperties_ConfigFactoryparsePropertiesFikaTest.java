/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import java.util.Collections;

import org.junit.jupiter.api.Test;

class EnvironmentsystemProperties_ConfigFactoryparsePropertiesFikaTest {

  @Test
  void testLoadEnvironment() {
    EnvironmentOptions options = new EnvironmentOptions();
    options.setActiveNames(Collections.singletonList("test"));

    Environment.loadEnvironment(options);
  }
}
