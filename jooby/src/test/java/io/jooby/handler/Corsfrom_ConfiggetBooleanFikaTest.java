/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.handler;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Corsfrom_ConfiggetBooleanFikaTest {

  @Test
  public void testFromConfigTriggersGetBoolean() {
    // Create a Config that has a "cors.credentials" path to trigger getBoolean
    String configText = "cors {\n" + "  credentials = true\n" + "}";
    Config config = ConfigFactory.parseString(configText);

    // Call the static entry point method
    Cors.from(config);
  }
}
