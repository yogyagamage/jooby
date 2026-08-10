/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.typesafe.config.Config;

public class Joobyready_ConfiggetAnyRefFikaTest {

  @Test
  public void testReadyInvokesConfigGetAnyRef() throws Exception {
    Jooby app = new Jooby();

    Environment env = Mockito.mock(Environment.class);
    Config config = Mockito.mock(Config.class);

    Mockito.when(env.getConfig()).thenReturn(config);
    Mockito.when(config.hasPath(Mockito.anyString())).thenReturn(true);
    Mockito.when(config.getAnyRef(Mockito.anyString())).thenReturn("test");

    java.lang.reflect.Field envField = Jooby.class.getDeclaredField("env");
    envField.setAccessible(true);
    envField.set(app, env);

    Server server = Mockito.mock(Server.class);
    app.ready(server);
  }
}
