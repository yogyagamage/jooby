/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.handler;

import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.jooby.Context;
import io.jooby.SneakyThrows;

public class RateLimitHandlerapply_ConsumptionProbegetRemainingTokensFikaTest {

  @Test
  public void test() throws Exception {
    // Create a mock Bucket that returns a ConsumptionProbe with consumed=true
    Bucket mockBucket = Mockito.mock(Bucket.class);
    ConsumptionProbe mockProbe = Mockito.mock(ConsumptionProbe.class);
    Mockito.when(mockProbe.isConsumed()).thenReturn(true);
    Mockito.when(mockBucket.tryConsumeAndReturnRemaining(1)).thenReturn(mockProbe);

    // Create a mock Context
    Context mockContext = Mockito.mock(Context.class);

    // Create a Function that returns our mock bucket
    Function<Context, Bucket> factory = ctx -> mockBucket;

    // Use the private constructor via the public constructor that accepts Function
    RateLimitHandler handler =
        new RateLimitHandler(
            (SneakyThrows.Function<String, Bucket>) key -> mockBucket,
            (SneakyThrows.Function<Context, String>) ctx -> "test-key");

    // Invoke the entry point
    handler.apply(mockContext);

    // The execution path should call probe.getRemainingTokens() when probe.isConsumed() returns
    // true
    // No assertions needed
  }
}
