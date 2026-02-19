/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.handler;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Corsfrom_ConfiggetConfigFikaTest {

  @Test
  public void testFromMethodInvokesGetConfig() {
    // Create a real Config object with a "cors" path
    String configString = "cors { origin: \"*\" }";
    Config config = ConfigFactory.parseString(configString);

    // This will invoke conf.getConfig("cors") inside the from() method
    Cors.from(config);
  }
}
