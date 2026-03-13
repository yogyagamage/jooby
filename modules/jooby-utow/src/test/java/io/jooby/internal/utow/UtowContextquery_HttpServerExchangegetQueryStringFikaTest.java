package io.jooby.internal.utow;

import io.jooby.QueryString;
import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowContextquery_HttpServerExchangegetQueryStringFikaTest {

    @Test
    public void testQueryCallsGetQueryString() {
        // Create mock HttpServerExchange
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        
        // Setup the exchange to return a non-null query string
        when(exchange.getQueryString()).thenReturn("param=value");
        when(exchange.getRequestMethod()).thenReturn(io.undertow.util.HttpString.tryFromString("GET"));
        when(exchange.getRequestPath()).thenReturn("/test");
        
        // Create mock Router
        Router router = Mockito.mock(Router.class);
        
        // Instantiate UtowContext using the constructor
        UtowContext context = new UtowContext(exchange, router);
        
        // Call the entry point method - this should trigger exchange.getQueryString()
        QueryString result = context.query();
    }
}
