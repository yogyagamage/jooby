/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

class EnvironmentsystemProperties_ConfigFactoryparsePropertiesFikaTest {

  @Test
  void test() {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    Config config = ConfigFactory.empty();
    Environment environment = new Environment(classLoader, config, List.of("test"));
    Environment.systemProperties();
  }
}
