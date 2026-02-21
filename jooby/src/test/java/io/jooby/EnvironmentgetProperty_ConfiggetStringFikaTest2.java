/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

class EnvironmentgetProperty_ConfiggetStringFikaTest2 {

  @Test
  void test() {
    Config config = ConfigFactory.parseString("testKey = testValue");
    Environment environment = new Environment(getClass().getClassLoader(), config, "test");
    environment.getProperty("testKey");
  }
}
