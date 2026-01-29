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
    // Create a real Config with a "cors.credentials" path
    String configString = "cors {\n" + "  credentials = true\n" + "}";
    Config config = ConfigFactory.parseString(configString);

    // Execute the entry point method which should trigger Config.getBoolean("credentials")
    Cors.from(config);
  }
}
