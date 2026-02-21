/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Joobyready_ConfighasPathFikaTest {

  @Test
  public void ready_invokesConfigHasPath() {
    // Create a real Config that will be used by the ready method
    Map<String, Object> configMap = new HashMap<>();
    configMap.put("application.startupSummary", java.util.List.of("default"));
    Config config = ConfigFactory.parseMap(configMap);

    // Create mock Environment with the real Config
    Environment env = Mockito.mock(Environment.class);
    when(env.getConfig()).thenReturn(config);

    // Create Jooby instance with the mock Environment
    Jooby jooby = new Jooby();
    // Use reflection to set the private environment field
    try {
      var field = Jooby.class.getDeclaredField("env");
      field.setAccessible(true);
      field.set(jooby, env);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    // Create mock Server
    Server server = Mockito.mock(Server.class);

    // Invoke the entry point method
    jooby.ready(server);
  }
}
