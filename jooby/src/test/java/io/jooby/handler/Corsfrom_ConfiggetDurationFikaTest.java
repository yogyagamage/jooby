/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.handler;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Corsfrom_ConfiggetDurationFikaTest {

  @Test
  public void testGetDurationInvocation() {
    String configString = "cors { maxAge: 30m }";
    Config config = ConfigFactory.parseString(configString);

    Cors.from(config);
  }
}
