/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;

public class Environmentdefaults_ConfigFactoryparseMapFikaTest {

  @Test
  public void testDefaults() {
    // Directly call the static method to reach ConfigFactory.parseMap
    Config config = Environment.defaults();
  }
}
