/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;
import io.jooby.problem.ProblemDetailsHandler;

class JoobyproblemDetailsIsEnabled_ConfighasPathFikaTest {

  @Test
  void testProblemDetailsIsEnabled() {
    Jooby jooby = new Jooby();

    Config mockConfig = mock(Config.class);
    when(mockConfig.hasPath(ProblemDetailsHandler.ENABLED_KEY)).thenReturn(false);

    jooby.problemDetailsIsEnabled();
  }
}
