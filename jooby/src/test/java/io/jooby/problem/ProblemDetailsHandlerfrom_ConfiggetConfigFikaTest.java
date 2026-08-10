/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.problem;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ProblemDetailsHandlerfrom_ConfiggetConfigFikaTest {

  @Test
  public void testFromConfigTriggersGetConfig() {
    // Create a real Config object with the required path
    String configString = "problem.details { log4xxErrors = true }";
    Config config = ConfigFactory.parseString(configString);

    // Invoke the entry point method
    ProblemDetailsHandler.from(config);
  }
}
