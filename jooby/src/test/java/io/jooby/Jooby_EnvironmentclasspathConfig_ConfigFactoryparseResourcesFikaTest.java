/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import java.util.Collections;

import org.junit.jupiter.api.Test;

public class Jooby_EnvironmentclasspathConfig_ConfigFactoryparseResourcesFikaTest {

  @Test
  public void testSetEnvironmentOptionsTriggersParseResources() {
    Jooby jooby = new Jooby();

    EnvironmentOptions options = new EnvironmentOptions();
    options.setFilename("application.conf");
    options.setActiveNames(Collections.singletonList("dev"));

    jooby.setEnvironmentOptions(options);
  }
}
