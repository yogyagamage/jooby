package io.jooby.internal.utow;

import io.jooby.MediaType;
import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.charset.StandardCharsets;

public class UtowContextifSetChunked_HttpServerExchangegetResponseHeadersFikaTest {

    @Test
    public void testResponseWriterCallsIfSetChunkedWhichCallsGetResponseHeaders() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        HeaderMap responseHeaders = new HeaderMap();
        responseHeaders.put(Headers.CONTENT_LENGTH, "100");
        
        Mockito.when(exchange.getResponseHeaders()).thenReturn(responseHeaders);
        Mockito.when(exchange.getRequestMethod()).thenReturn(io.undertow.util.HttpString.tryFromString("GET"));
        Mockito.when(exchange.getRequestPath()).thenReturn("/test");
        
        UtowContext context = new UtowContext(exchange, router);
        context.responseWriter(MediaType.text, StandardCharsets.UTF_8);
    }
}
