package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowContextmethod_HttpServerExchangegetRequestPathFikaTest {

    @Test
    public void test() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        when(exchange.getRequestMethod()).thenReturn(io.undertow.util.HttpString.tryFromString("GET"));
        when(exchange.getRequestPath()).thenReturn("/test");
        
        UtowContext context = new UtowContext(exchange, router);
    }
}
