/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ServerOptionsfrom_ConfiggetMemorySizeFikaTest {

  @Test
  public void test() {
    Map<String, Object> configMap = new HashMap<>();
    configMap.put("server.maxRequestSize", "128k");
    configMap.put("server.port", "8080");

    Config config = ConfigFactory.parseMap(configMap);

    ServerOptions.from(config);
  }
}
