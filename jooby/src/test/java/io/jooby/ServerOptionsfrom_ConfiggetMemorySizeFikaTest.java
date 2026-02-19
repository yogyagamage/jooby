/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ServerOptionsfrom_ConfiggetMemorySizeFikaTest {

  @Test
  public void testGetMemorySizeInvocation() {
    String configText = "server {\n" + "  maxRequestSize = 1024\n" + "  port = 8080\n" + "}";

    Config conf = ConfigFactory.parseString(configText);
    ServerOptions.from(conf);
  }
}
