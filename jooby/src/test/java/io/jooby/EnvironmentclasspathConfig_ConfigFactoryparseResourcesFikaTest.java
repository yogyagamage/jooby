/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EnvironmentclasspathConfig_ConfigFactoryparseResourcesFikaTest {

  @Test
  public void testLoadEnvironmentTriggersParseResources() {
    // Create EnvironmentOptions mock
    EnvironmentOptions options = Mockito.mock(EnvironmentOptions.class);

    // Configure the mock to return values that will lead to classpathConfig being called
    Mockito.when(options.getActiveNames()).thenReturn(java.util.Collections.emptyList());
    Mockito.when(options.getFilename()).thenReturn("application.conf");
    Mockito.when(options.getBasedir()).thenReturn(null);
    Mockito.when(options.getClassLoader())
        .thenReturn(Thread.currentThread().getContextClassLoader());

    // Call the entry point method
    Environment.loadEnvironment(options);
  }
}
