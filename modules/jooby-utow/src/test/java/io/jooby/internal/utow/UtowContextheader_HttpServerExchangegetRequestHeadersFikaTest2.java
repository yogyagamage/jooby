package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowContextheader_HttpServerExchangegetRequestHeadersFikaTest2 {

    @Test
    public void testHeaderCallsGetRequestHeaders() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        HttpString requestMethod = HttpString.tryFromString("GET");
        when(exchange.getRequestMethod()).thenReturn(requestMethod);
        
        HeaderMap headerMap = Mockito.mock(HeaderMap.class);
        when(exchange.getRequestHeaders()).thenReturn(headerMap);
        
        UtowContext context = new UtowContext(exchange, router);
        context.header();
    }
}
