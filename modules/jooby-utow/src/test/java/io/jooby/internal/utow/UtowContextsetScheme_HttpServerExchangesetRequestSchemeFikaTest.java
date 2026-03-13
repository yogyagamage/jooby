package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowContextsetScheme_HttpServerExchangesetRequestSchemeFikaTest {

    @Test
    public void testSetScheme() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        when(exchange.getRequestMethod()).thenReturn(HttpString.tryFromString("GET"));
        when(exchange.getRequestPath()).thenReturn("/test");
        
        UtowContext context = new UtowContext(exchange, router);
        context.setScheme("https");
    }
}
