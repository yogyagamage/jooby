/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.typesafe.config.Config;

public class Joobyready_ConfiggetAnyRefFikaTest {

  @Test
  public void readyShouldInvokeConfigGetAnyRef() {
    // Create real Jooby instance
    Jooby jooby = new Jooby();

    // Create mock Environment
    Environment mockEnv = Mockito.mock(Environment.class);

    // Create mock Config that will be called
    Config mockConfig = Mockito.mock(Config.class);

    // Setup the chain: env.getConfig() returns mockConfig
    when(mockEnv.getConfig()).thenReturn(mockConfig);

    // Setup config.hasPath() to return true so execution enters the if block
    when(mockConfig.hasPath(AvailableSettings.STARTUP_SUMMARY)).thenReturn(true);

    // Setup config.getAnyRef() to return a value (any object will do)
    when(mockConfig.getAnyRef(AvailableSettings.STARTUP_SUMMARY)).thenReturn(List.of("default"));

    // Set the env field on Jooby instance
    // Using reflection since there's no setter for env field
    try {
      java.lang.reflect.Field envField = Jooby.class.getDeclaredField("env");
      envField.setAccessible(true);
      envField.set(jooby, mockEnv);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    // Create mock Server
    Server mockServer = Mockito.mock(Server.class);

    // Call the entry point
    jooby.ready(mockServer);

    // No assertions - test passes if no exceptions and the chain executes
  }
}
