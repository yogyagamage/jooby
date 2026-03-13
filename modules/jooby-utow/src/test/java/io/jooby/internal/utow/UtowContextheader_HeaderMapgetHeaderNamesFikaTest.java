package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowContextheader_HeaderMapgetHeaderNamesFikaTest {

    @Test
    public void testHeaderMethodInvokesGetHeaderNames() {
        // Create mock HttpServerExchange
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        
        // Create mock Router
        Router router = Mockito.mock(Router.class);
        
        // Setup required methods on exchange to avoid NPE in constructor
        HttpString requestMethod = Mockito.mock(HttpString.class);
        when(requestMethod.toString()).thenReturn("GET");
        when(exchange.getRequestMethod()).thenReturn(requestMethod);
        when(exchange.getRequestPath()).thenReturn("/test");
        
        // Setup request headers to ensure getHeaderNames() is called
        HeaderMap headerMap = Mockito.mock(HeaderMap.class);
        when(exchange.getRequestHeaders()).thenReturn(headerMap);
        
        // Create UtowContext instance using the constructor
        UtowContext context = new UtowContext(exchange, router);
        
        // Call the entry point method
        context.header();
    }
}
