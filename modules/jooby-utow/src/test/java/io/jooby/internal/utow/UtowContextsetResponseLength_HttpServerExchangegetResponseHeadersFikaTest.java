package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.undertow.util.Headers.CONTENT_LENGTH;

public class UtowContextsetResponseLength_HttpServerExchangegetResponseHeadersFikaTest {

    @Test
    public void testSetResponseLengthCallsGetResponseHeaders() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        HttpString requestMethod = new HttpString("GET");
        Mockito.when(exchange.getRequestMethod()).thenReturn(requestMethod);
        Mockito.when(exchange.getRequestPath()).thenReturn("/test");
        
        Mockito.when(exchange.getResponseHeaders()).thenReturn(Mockito.mock(io.undertow.util.HeaderMap.class));
        
        UtowContext context = new UtowContext(exchange, router);
        context.setResponseLength(1024L);
    }
}
