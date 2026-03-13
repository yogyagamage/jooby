package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.ByteBuffer;

public class UtowContextsend_SendersendFikaTest {

    @Test
    public void testSendPath() {
        // Mock HttpServerExchange to avoid NPE in constructor
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        
        // Setup required method calls to avoid NPE
        HttpString requestMethod = Mockito.mock(HttpString.class);
        Mockito.when(requestMethod.toString()).thenReturn("GET");
        Mockito.when(exchange.getRequestMethod()).thenReturn(requestMethod);
        Mockito.when(exchange.getRequestPath()).thenReturn("/test");
        
        // Mock the response sender that will be called in send()
        io.undertow.io.Sender sender = Mockito.mock(io.undertow.io.Sender.class);
        Mockito.when(exchange.getResponseSender()).thenReturn(sender);
        
        // Mock response headers
        io.undertow.util.HeaderMap headerMap = Mockito.mock(io.undertow.util.HeaderMap.class);
        Mockito.when(exchange.getResponseHeaders()).thenReturn(headerMap);
        
        // Mock router
        Router router = Mockito.mock(Router.class);
        
        // Create instance using constructor
        UtowContext context = new UtowContext(exchange, router);
        
        // Create ByteBuffer to send
        ByteBuffer buffer = ByteBuffer.wrap("test".getBytes());
        
        // Call entry point method - this should trigger the third-party method call
        context.send(buffer);
    }
}
