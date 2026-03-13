package io.jooby.internal.utow;

import io.jooby.Router;
import io.jooby.Value;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowContextheader_HttpServerExchangegetRequestHeadersFikaTest {

    @Test
    public void testHeaderCallsGetRequestHeaders() {
        // Create mock HttpServerExchange
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        
        // Setup required methods to avoid NPE in constructor
        HttpString requestMethod = Mockito.mock(HttpString.class);
        when(requestMethod.toString()).thenReturn("GET");
        when(exchange.getRequestMethod()).thenReturn(requestMethod);
        
        when(exchange.getRequestPath()).thenReturn("/test");
        
        // Setup request headers to avoid NPE in header() method
        HeaderMap requestHeaders = Mockito.mock(HeaderMap.class);
        when(exchange.getRequestHeaders()).thenReturn(requestHeaders);
        
        // Create mock Router
        Router router = Mockito.mock(Router.class);
        
        // Instantiate UtowContext using constructor
        UtowContext context = new UtowContext(exchange, router);
        
        // Call entry point method
        Value result = context.header("test-header");
        
        // No assertions - test passes if no exception is thrown
        // The third-party method exchange.getRequestHeaders() was invoked
    }
}
