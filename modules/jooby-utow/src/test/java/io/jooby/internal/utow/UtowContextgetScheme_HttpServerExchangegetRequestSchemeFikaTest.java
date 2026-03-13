package io.jooby.internal.utow;

import io.undertow.server.HttpServerExchange;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowContextgetScheme_HttpServerExchangegetRequestSchemeFikaTest {

    @Test
    public void testGetScheme() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        when(exchange.getRequestMethod()).thenReturn(io.undertow.util.HttpString.tryFromString("GET"));
        when(exchange.getRequestPath()).thenReturn("/");
        
        UtowContext context = new UtowContext(exchange, router);
        
        context.getScheme();
    }
}
