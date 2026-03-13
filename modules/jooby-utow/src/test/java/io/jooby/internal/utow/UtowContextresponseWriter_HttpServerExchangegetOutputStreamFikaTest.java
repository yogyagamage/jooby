package io.jooby.internal.utow;

import io.jooby.MediaType;
import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.charset.StandardCharsets;

public class UtowContextresponseWriter_HttpServerExchangegetOutputStreamFikaTest {

    @Test
    public void testResponseWriterCallsGetOutputStream() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        Mockito.when(exchange.getRequestMethod()).thenReturn(HttpString.tryFromString("GET"));
        Mockito.when(exchange.getRequestPath()).thenReturn("/test");
        Mockito.when(exchange.isBlocking()).thenReturn(false);
        Mockito.when(exchange.getResponseHeaders()).thenReturn(Mockito.mock(io.undertow.util.HeaderMap.class));
        
        UtowContext context = new UtowContext(exchange, router);
        context.responseWriter(MediaType.text, StandardCharsets.UTF_8);
    }
}
