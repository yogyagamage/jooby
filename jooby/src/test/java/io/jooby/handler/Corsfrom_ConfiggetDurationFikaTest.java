/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.handler;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Corsfrom_ConfiggetDurationFikaTest {

  @Test
  public void testFromConfigTriggersGetDuration() {
    Map<String, Object> configMap = new HashMap<>();
    Map<String, Object> corsMap = new HashMap<>();
    corsMap.put("maxAge", "30m");
    configMap.put("cors", corsMap);

    Config config = ConfigFactory.parseMap(configMap);
    Cors.from(config);
  }
}
