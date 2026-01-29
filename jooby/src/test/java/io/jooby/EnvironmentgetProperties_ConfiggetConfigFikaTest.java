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

class EnvironmentgetProperties_ConfiggetConfigFikaTest {

  @Test
  void testGetPropertiesCallsConfigGetConfig() {
    Map<String, Object> configMap = new HashMap<>();
    configMap.put("test.key.subkey", "value");

    Config config = ConfigFactory.parseMap(configMap);
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    Environment environment = new Environment(classLoader, config, "test");

    environment.getProperties("test.key", "prefix");
  }
}
