package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.OutputStream;

import static org.mockito.Mockito.when;

public class UtowContextifSetChunked_HeaderMapcontainsFikaTest3 {

    @Test
    public void testResponseStreamTriggersHeaderMapContains() throws Exception {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        HttpString requestMethod = Mockito.mock(HttpString.class);
        when(requestMethod.toString()).thenReturn("GET");
        when(exchange.getRequestMethod()).thenReturn(requestMethod);
        when(exchange.getRequestPath()).thenReturn("/test");
        
        HeaderMap responseHeaders = Mockito.mock(HeaderMap.class);
        when(exchange.getResponseHeaders()).thenReturn(responseHeaders);
        
        when(responseHeaders.contains(Headers.CONTENT_LENGTH)).thenReturn(false);
        
        OutputStream outputStream = Mockito.mock(OutputStream.class);
        when(exchange.getOutputStream()).thenReturn(outputStream);
        
        UtowContext context = new UtowContext(exchange, router);
        context.responseStream();
    }
}
