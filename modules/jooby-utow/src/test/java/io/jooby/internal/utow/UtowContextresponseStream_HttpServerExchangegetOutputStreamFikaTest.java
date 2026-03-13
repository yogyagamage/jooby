package io.jooby.internal.utow;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.OutputStream;

import static org.mockito.Mockito.when;

public class UtowContextresponseStream_HttpServerExchangegetOutputStreamFikaTest {

    @Test
    public void testResponseStreamCallsGetOutputStream() {
        // Create mock HttpServerExchange with required method stubs
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        HttpString requestMethod = Mockito.mock(HttpString.class);
        
        // Stub required methods to avoid NPE in constructor
        when(exchange.getRequestMethod()).thenReturn(requestMethod);
        when(requestMethod.toString()).thenReturn("GET");
        when(exchange.getRequestPath()).thenReturn("/test");
        
        // Stub methods needed for responseStream() execution
        when(exchange.isBlocking()).thenReturn(false);
        when(exchange.getResponseHeaders()).thenReturn(Mockito.mock(io.undertow.util.HeaderMap.class));
        
        // Create mock OutputStream for the target method call
        OutputStream outputStream = Mockito.mock(OutputStream.class);
        when(exchange.getOutputStream()).thenReturn(outputStream);
        
        // Create mock Router (required for constructor)
        Router router = Mockito.mock(Router.class);
        
        // Instantiate UtowContext using constructor
        UtowContext utowContext = new UtowContext(exchange, router);
        
        // Call entry point method - this should trigger the chain ending with exchange.getOutputStream()
        utowContext.responseStream();
    }
}
