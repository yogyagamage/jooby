package io.jooby.internal.utow;

import io.jooby.Router;
import io.jooby.Value;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowContextheader_HeaderMapgetFikaTest {

    @Test
    public void testHeaderCallsHeaderMapGet() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        HttpString requestMethod = Mockito.mock(HttpString.class);
        when(requestMethod.toString()).thenReturn("GET");
        when(exchange.getRequestMethod()).thenReturn(requestMethod);
        
        HeaderMap requestHeaders = Mockito.mock(HeaderMap.class);
        when(exchange.getRequestHeaders()).thenReturn(requestHeaders);
        
        UtowContext context = new UtowContext(exchange, router);
        
        context.header("test-header");
    }
}
