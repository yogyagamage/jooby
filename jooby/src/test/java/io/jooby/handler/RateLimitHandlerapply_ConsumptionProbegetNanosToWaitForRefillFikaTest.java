/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.handler;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.jooby.Context;
import io.jooby.SneakyThrows;

public class RateLimitHandlerapply_ConsumptionProbegetNanosToWaitForRefillFikaTest {

  @Test
  public void testApplyTriggersGetNanosToWaitForRefill() throws Exception {
    // Create mock Bucket that returns a ConsumptionProbe where isConsumed() returns false
    Bucket mockBucket = Mockito.mock(Bucket.class);
    ConsumptionProbe mockProbe = Mockito.mock(ConsumptionProbe.class);
    Mockito.when(mockProbe.isConsumed()).thenReturn(false);
    Mockito.when(mockProbe.getNanosToWaitForRefill()).thenReturn(1000L);
    Mockito.when(mockBucket.tryConsumeAndReturnRemaining(1)).thenReturn(mockProbe);

    // Create mock Context
    Context mockContext = Mockito.mock(Context.class);

    // Create factory function that returns our mock bucket
    SneakyThrows.Function<String, Bucket> bucketFactory = key -> mockBucket;

    // Create classifier function that returns a key
    SneakyThrows.Function<Context, String> classifier = ctx -> "test-key";

    // Instantiate RateLimitHandler using constructor with bucketFactory and classifier
    RateLimitHandler handler = new RateLimitHandler(bucketFactory, classifier);

    // Call the entry point method
    handler.apply(mockContext);

    // The test will execute the full chain:
    // 1. handler.apply(mockContext)
    // 2. factory.apply(ctx) returns mockBucket
    // 3. mockBucket.tryConsumeAndReturnRemaining(1) returns mockProbe
    // 4. mockProbe.isConsumed() returns false
    // 5. NANOSECONDS.toMillis(probe.getNanosToWaitForRefill()) triggers getNanosToWaitForRefill()
  }
}
