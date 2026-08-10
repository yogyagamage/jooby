/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.typesafe.config.Config;
import io.jooby.problem.ProblemDetailsHandler;

public class JoobyproblemDetailsIsEnabled_ConfiggetBooleanFikaTest {

  @Test
  public void testProblemDetailsIsEnabled() {
    // Create a mock Config that will be returned by getConfig()
    Config mockConfig = Mockito.mock(Config.class);
    Mockito.when(mockConfig.hasPath(ProblemDetailsHandler.ENABLED_KEY)).thenReturn(true);
    Mockito.when(mockConfig.getBoolean(ProblemDetailsHandler.ENABLED_KEY)).thenReturn(true);

    // Create Jooby instance using the provided constructor
    Jooby jooby = new Jooby();

    // Set up internal state to ensure getConfig() returns our mock
    // We need to set the environment and registry to provide the mock config
    Environment mockEnv = Mockito.mock(Environment.class);
    Mockito.when(mockEnv.getConfig()).thenReturn(mockConfig);

    // Use reflection to set the private env field
    try {
      java.lang.reflect.Field envField = Jooby.class.getDeclaredField("env");
      envField.setAccessible(true);
      envField.set(jooby, mockEnv);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    // Call the entry point method - this should trigger the chain
    // that eventually calls config.getBoolean(ProblemDetailsHandler.ENABLED_KEY)
    jooby.problemDetailsIsEnabled();
  }
}
