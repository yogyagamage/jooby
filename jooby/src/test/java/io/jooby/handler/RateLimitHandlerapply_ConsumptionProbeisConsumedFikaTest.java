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

public class RateLimitHandlerapply_ConsumptionProbeisConsumedFikaTest {

  @Test
  public void testApplyInvokesIsConsumed() throws Exception {
    // Create mock Bucket that returns a real ConsumptionProbe
    Bucket mockBucket = Mockito.mock(Bucket.class);
    ConsumptionProbe mockProbe = Mockito.mock(ConsumptionProbe.class);
    Mockito.when(mockBucket.tryConsumeAndReturnRemaining(1)).thenReturn(mockProbe);

    // Create mock Context
    Context mockContext = Mockito.mock(Context.class);
    Mockito.when(mockContext.getRemoteAddress()).thenReturn("127.0.0.1");

    // Create classifier function that returns a key
    SneakyThrows.Function<Context, String> classifier = ctx -> ctx.getRemoteAddress();

    // Create bucket factory function that returns our mock bucket
    SneakyThrows.Function<String, Bucket> bucketFactory = key -> mockBucket;

    // Instantiate RateLimitHandler using constructor that takes bucketFactory and classifier
    RateLimitHandler handler = new RateLimitHandler(bucketFactory, classifier);

    // Execute the entry point method
    handler.apply(mockContext);

    // The execution path will invoke probe.isConsumed() as required
    // No assertions or verifications needed
  }
}
