/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.internal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.Test;

import io.jooby.ServiceKey;
import jakarta.inject.Provider;

class ServiceRegistryImplgetOrNull_ProvidergetFikaTest {

  @Test
  void test() {
    ServiceRegistryImpl serviceRegistry = new ServiceRegistryImpl();

    ServiceKey<String> key = ServiceKey.key(String.class);
    Provider<String> provider = () -> "test";

    Map<ServiceKey<?>, Provider<?>> registry = new ConcurrentHashMap<>();
    registry.put(key, provider);

    // Use reflection to set the private registry field
    try {
      var field = ServiceRegistryImpl.class.getDeclaredField("registry");
      field.setAccessible(true);
      field.set(serviceRegistry, registry);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    serviceRegistry.getOrNull(key);
  }
}
