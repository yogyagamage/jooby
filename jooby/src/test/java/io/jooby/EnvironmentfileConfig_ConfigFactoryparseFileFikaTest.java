/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class EnvironmentfileConfig_ConfigFactoryparseFileFikaTest {

  @Test
  public void testLoadEnvironmentTriggersParseFile(@TempDir Path tempDir) throws Exception {
    // Create a conf directory and application.conf file
    Path confDir = tempDir.resolve("conf");
    Files.createDirectories(confDir);
    File configFile = confDir.resolve("application.conf").toFile();
    Files.write(configFile.toPath(), "test = 123".getBytes());

    // Set user.dir to tempDir so Environment finds our file
    System.setProperty("user.dir", tempDir.toString());

    // Create EnvironmentOptions with minimal configuration
    EnvironmentOptions options = new EnvironmentOptions();
    options.setActiveNames(Collections.singletonList("dev"));
    options.setFilename("application.conf");

    // This should trigger the full chain:
    // loadEnvironment -> resolveConfig -> fileConfig -> ConfigFactory.parseFile
    Environment.loadEnvironment(options);
  }
}
