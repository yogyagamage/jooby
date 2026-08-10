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

public class RateLimitHandlerapply_BuckettryConsumeAndReturnRemainingFikaTest {

  @Test
  public void test() throws Exception {
    // Create a real bucket that will allow consumption
    Bucket bucket = Mockito.mock(Bucket.class);
    ConsumptionProbe probe = Mockito.mock(ConsumptionProbe.class);
    Mockito.when(probe.isConsumed()).thenReturn(true);
    Mockito.when(probe.getRemainingTokens()).thenReturn(10L);
    Mockito.when(bucket.tryConsumeAndReturnRemaining(1)).thenReturn(probe);

    // Create factory that returns our bucket
    SneakyThrows.Function<String, Bucket> bucketFactory = key -> bucket;

    // Use constructor that takes bucket factory
    RateLimitHandler handler = new RateLimitHandler(bucketFactory);

    // Create mock context
    Context ctx = Mockito.mock(Context.class);
    Mockito.when(ctx.getRemoteAddress()).thenReturn("127.0.0.1");

    // Call entry point - this should trigger bucket.tryConsumeAndReturnRemaining(1)
    handler.apply(ctx);
  }
}
