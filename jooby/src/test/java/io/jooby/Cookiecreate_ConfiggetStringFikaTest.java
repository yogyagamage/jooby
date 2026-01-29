/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

class Cookiecreate_ConfiggetStringFikaTest {

  @Test
  void test() {
    Config config = ConfigFactory.parseString("test.name = \"cookieName\"");
    Optional<Cookie> result = Cookie.create("test", config);
  }
}
