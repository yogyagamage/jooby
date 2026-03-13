package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UtowContextremoveResponseHeaders_HeaderMapclearFikaTest {

    @Test
    public void testRemoveResponseHeadersCallsHeaderMapClear() {
        HttpServerExchange exchange = mock(HttpServerExchange.class);
        Router router = mock(Router.class);
        
        HttpString requestMethod = mock(HttpString.class);
        when(requestMethod.toString()).thenReturn("GET");
        when(exchange.getRequestMethod()).thenReturn(requestMethod);
        when(exchange.getRequestPath()).thenReturn("/test");
        
        HeaderMap responseHeaders = mock(HeaderMap.class);
        when(exchange.getResponseHeaders()).thenReturn(responseHeaders);
        
        UtowContext context = new UtowContext(exchange, router);
        context.removeResponseHeaders();
    }
}
