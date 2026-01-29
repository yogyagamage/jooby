/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.typesafe.config.Config;

public class Joobyready_ConfighasPathFikaTest {

  @Test
  public void ready_invokesConfigHasPath() {
    // Create Jooby instance using default constructor
    Jooby jooby = new Jooby();

    // Create mock Server
    Server server = Mockito.mock(Server.class);
    ServerOptions serverOptions = new ServerOptions();
    when(server.getOptions()).thenReturn(serverOptions);

    // Create mock Environment with mock Config
    Environment env = Mockito.mock(Environment.class);
    Config config = Mockito.mock(Config.class);
    when(env.getConfig()).thenReturn(config);

    // Set the environment field on Jooby instance
    // Using reflection to set private field since there's no setter
    try {
      java.lang.reflect.Field envField = Jooby.class.getDeclaredField("env");
      envField.setAccessible(true);
      envField.set(jooby, env);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    // Call the entry point method
    jooby.ready(server);

    // The test will execute the chain:
    // Jooby.ready(Server) -> env.getConfig() -> config.hasPath(String)
    // No assertions needed as per requirements
  }
}
