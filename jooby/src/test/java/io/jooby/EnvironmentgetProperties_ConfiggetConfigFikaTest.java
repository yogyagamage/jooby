/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class EnvironmentgetProperties_ConfiggetConfigFikaTest {

  @Test
  public void test() {
    Config config = ConfigFactory.parseString("test.sub.key = \"value\"");
    Environment environment =
        new Environment(Thread.currentThread().getContextClassLoader(), config, "dev");
    Map<String, String> result = environment.getProperties("test");
  }
}
