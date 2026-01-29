/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.handler;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Corsfrom_ConfiggetConfigFikaTest {

  @Test
  public void testFromConfigTriggersGetConfig() {
    // Create a real Config that has a "cors" path
    Config config = ConfigFactory.parseString("cors { origin: \"*\" }");

    // Execute the entry point method which should trigger
    // conf.getConfig("cors") inside Cors.from()
    Cors.from(config);
  }

  // Helper class to make the test compile
  // This is a simplified version of the Matcher class referenced in fieldDeclarations
  static class Matcher<T> {
    private List<String> values;
    private Predicate<T> predicate;

    Matcher(List<String> values, Predicate<T> predicate) {
      this.values = values;
      this.predicate = predicate;
    }
  }
}
