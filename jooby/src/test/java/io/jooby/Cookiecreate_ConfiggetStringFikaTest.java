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
    String hoconConfig =
        "testCookie {\n"
            + "  name = \"sessionId\"\n"
            + "  value = \"abc123\"\n"
            + "  path = \"/\"\n"
            + "  domain = \"example.com\"\n"
            + "  secure = true\n"
            + "  httpOnly = true\n"
            + "  maxAge = 1h\n"
            + "  sameSite = \"Lax\"\n"
            + "}";

    Config config = ConfigFactory.parseString(hoconConfig);
    Optional<Cookie> result = Cookie.create("testCookie", config);
  }
}
