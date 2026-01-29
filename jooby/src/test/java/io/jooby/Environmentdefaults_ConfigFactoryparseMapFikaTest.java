/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

class Environmentdefaults_ConfigFactoryparseMapFikaTest {

  @Test
  void testDefaults() {
    // Create a real Config object using ConfigFactory
    Map<String, String> configMap = new HashMap<>();
    configMap.put("some.key", "some.value");
    Config config = ConfigFactory.parseMap(configMap);

    // Create Environment instance using constructor
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    Environment environment = new Environment(classLoader, config, List.of("test"));

    // Call the static entry point method
    Environment.defaults();
  }
}
