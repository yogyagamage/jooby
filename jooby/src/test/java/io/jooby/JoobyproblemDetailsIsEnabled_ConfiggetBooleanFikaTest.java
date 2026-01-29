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
import io.jooby.problem.ProblemDetailsHandler;

public class JoobyproblemDetailsIsEnabled_ConfiggetBooleanFikaTest {

  @Test
  public void testProblemDetailsIsEnabled() {
    // Create a mock Config that will be returned by getConfig()
    Config mockConfig = Mockito.mock(Config.class);

    // Configure the mock to return true for hasPath check
    when(mockConfig.hasPath(ProblemDetailsHandler.ENABLED_KEY)).thenReturn(true);

    // Configure the mock to return a boolean value for getBoolean call
    when(mockConfig.getBoolean(ProblemDetailsHandler.ENABLED_KEY)).thenReturn(true);

    // Create Jooby instance using the default constructor
    Jooby jooby = new Jooby();

    // Use reflection to set the private env field with a mock Environment
    // that returns our mock Config
    Environment mockEnv = Mockito.mock(Environment.class);
    when(mockEnv.getConfig()).thenReturn(mockConfig);

    try {
      java.lang.reflect.Field envField = Jooby.class.getDeclaredField("env");
      envField.setAccessible(true);
      envField.set(jooby, mockEnv);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    // Call the entry point method
    jooby.problemDetailsIsEnabled();
  }
}
