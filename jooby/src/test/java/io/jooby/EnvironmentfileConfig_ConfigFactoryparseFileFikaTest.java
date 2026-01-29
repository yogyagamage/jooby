/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class EnvironmentfileConfig_ConfigFactoryparseFileFikaTest {

  @TempDir Path tempDir;

  @Test
  public void testLoadEnvironmentTriggersParseFile() throws Exception {
    // Create a configuration file that will be found by fileConfig
    Path confDir = tempDir.resolve("conf");
    Files.createDirectories(confDir);
    Path configFile = confDir.resolve("application.conf");
    Files.write(configFile, "test = 123".getBytes());

    // Set user.dir to tempDir so Environment.loadEnvironment finds our file
    String originalUserDir = System.getProperty("user.dir");
    System.setProperty("user.dir", tempDir.toString());

    try {
      // Create EnvironmentOptions with minimal configuration
      EnvironmentOptions options = new EnvironmentOptions();
      options.setFilename("application.conf");

      // This should trigger the full call chain:
      // loadEnvironment -> resolveConfig -> fileConfig -> ConfigFactory.parseFile
      Environment.loadEnvironment(options);
    } finally {
      // Restore original user.dir
      if (originalUserDir != null) {
        System.setProperty("user.dir", originalUserDir);
      }
    }
  }
}
