/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;

class Cookiecreate_ConfiggetStringFikaTest {

  @Test
  void testCreateMethodInvokesConfigGetString() {
    Config config = mock(Config.class);
    String namespace = "testCookie";
    String propertyPath = namespace + ".name";
    String cookieName = "sessionId";

    when(config.hasPath(namespace)).thenReturn(true);
    when(config.getString(propertyPath)).thenReturn(cookieName);

    Optional<Cookie> result = Cookie.create(namespace, config);
  }
}
