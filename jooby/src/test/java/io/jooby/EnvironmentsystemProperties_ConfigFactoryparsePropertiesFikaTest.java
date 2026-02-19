/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;

public class EnvironmentsystemProperties_ConfigFactoryparsePropertiesFikaTest {

  @Test
  public void testSystemProperties() {
    // Direct invocation of the static method that contains the target third-party method
    Config config = Environment.systemProperties();

    // No assertions or verifications - just ensure the method is called
    // The call above will trigger ConfigFactory.parseProperties(...) internally
  }
}
