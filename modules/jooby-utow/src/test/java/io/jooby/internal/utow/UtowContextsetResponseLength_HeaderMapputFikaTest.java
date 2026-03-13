package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.undertow.util.Headers.CONTENT_LENGTH;

public class UtowContextsetResponseLength_HeaderMapputFikaTest {

    @Test
    public void testSetResponseLengthCallsHeaderMapPut() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        HttpString requestMethod = Mockito.mock(HttpString.class);
        Mockito.when(requestMethod.toString()).thenReturn("GET");
        Mockito.when(exchange.getRequestMethod()).thenReturn(requestMethod);
        
        HeaderMap responseHeaders = Mockito.mock(HeaderMap.class);
        Mockito.when(exchange.getResponseHeaders()).thenReturn(responseHeaders);
        
        UtowContext context = new UtowContext(exchange, router);
        
        context.setResponseLength(1024L);
    }
}
