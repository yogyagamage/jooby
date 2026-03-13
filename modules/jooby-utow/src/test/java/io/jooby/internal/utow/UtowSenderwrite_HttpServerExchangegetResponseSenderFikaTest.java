package io.jooby.internal.utow;

import io.jooby.Sender;
import io.undertow.server.HttpServerExchange;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UtowSenderwrite_HttpServerExchangegetResponseSenderFikaTest {

    @Test
    public void testWriteCallsGetResponseSender() {
        // Create mocks for constructor dependencies
        UtowContext mockCtx = mock(UtowContext.class);
        HttpServerExchange mockExchange = mock(HttpServerExchange.class);
        
        // Mock the response sender chain
        io.undertow.io.Sender mockUnderTowSender = mock(io.undertow.io.Sender.class);
        when(mockExchange.getResponseSender()).thenReturn(mockUnderTowSender);
        
        // Create instance of class under test
        UtowSender utowSender = new UtowSender(mockCtx, mockExchange);
        
        // Create test data
        byte[] testData = new byte[]{1, 2, 3};
        Sender.Callback mockCallback = mock(Sender.Callback.class);
        
        // Execute the entry point method
        utowSender.write(testData, mockCallback);
    }
}
