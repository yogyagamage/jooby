package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UtowContextsetResponseHeader_HeaderMapputFikaTest {

    @Test
    public void testSetResponseHeaderCallsHeaderMapPut() {
        HttpServerExchange exchange = mock(HttpServerExchange.class);
        Router router = mock(Router.class);
        
        HttpString requestMethod = HttpString.tryFromString("GET");
        when(exchange.getRequestMethod()).thenReturn(requestMethod);
        
        io.undertow.util.HeaderMap responseHeaders = mock(io.undertow.util.HeaderMap.class);
        when(exchange.getResponseHeaders()).thenReturn(responseHeaders);
        
        UtowContext context = new UtowContext(exchange, router);
        
        context.setResponseHeader("Test-Header", "Test-Value");
    }
}
