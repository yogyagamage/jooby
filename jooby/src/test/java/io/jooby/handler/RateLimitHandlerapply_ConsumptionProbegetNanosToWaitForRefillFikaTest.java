/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby.handler;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.jooby.Context;
import io.jooby.SneakyThrows;

public class RateLimitHandlerapply_ConsumptionProbegetNanosToWaitForRefillFikaTest {

  @Test
  public void test() throws Exception {
    // Create a mock Bucket that returns a ConsumptionProbe where isConsumed() returns false
    Bucket mockBucket = Mockito.mock(Bucket.class);
    ConsumptionProbe mockProbe = Mockito.mock(ConsumptionProbe.class);
    Mockito.when(mockProbe.isConsumed()).thenReturn(false);
    Mockito.when(mockProbe.getNanosToWaitForRefill()).thenReturn(1000L);
    Mockito.when(mockBucket.tryConsumeAndReturnRemaining(1)).thenReturn(mockProbe);

    // Create a mock Context
    Context mockContext = Mockito.mock(Context.class);
    Mockito.when(mockContext.getRemoteAddress()).thenReturn("127.0.0.1");

    // Create a ConcurrentHashMap to store buckets
    ConcurrentHashMap<String, Bucket> bucketMap = new ConcurrentHashMap<>();
    bucketMap.put("127.0.0.1", mockBucket);

    // Create the bucket factory function using the map
    SneakyThrows.Function<String, Bucket> bucketFactory = key -> bucketMap.get(key);

    // Create RateLimitHandler using the constructor that takes bucketFactory and classifier
    RateLimitHandler handler = new RateLimitHandler(bucketFactory, Context::getRemoteAddress);

    // Call the entry point method
    handler.apply(mockContext);
  }
}
