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

public class RateLimitHandlerapply_ConsumptionProbegetRemainingTokensFikaTest {

  @Test
  public void test() throws Exception {
    // Create a mock Bucket that returns a ConsumptionProbe with tokens consumed
    Bucket mockBucket = Mockito.mock(Bucket.class);
    ConsumptionProbe mockProbe = Mockito.mock(ConsumptionProbe.class);
    Mockito.when(mockProbe.isConsumed()).thenReturn(true);
    Mockito.when(mockBucket.tryConsumeAndReturnRemaining(1)).thenReturn(mockProbe);

    // Create a mock Context
    Context mockContext = Mockito.mock(Context.class);

    // Create a Function that returns our mock bucket
    Function<Context, Bucket> bucketFunction = ctx -> mockBucket;

    // Use the private constructor via the public constructor that accepts a Bucket
    RateLimitHandler handler = new RateLimitHandler(mockBucket);

    // Invoke the entry point method
    handler.apply(mockContext);

    // The test will execute probe.getRemainingTokens() when probe.isConsumed() returns true
  }
}
