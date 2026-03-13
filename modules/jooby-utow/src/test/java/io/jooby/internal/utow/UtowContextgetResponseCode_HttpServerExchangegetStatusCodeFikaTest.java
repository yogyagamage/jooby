package io.jooby.internal.utow;

import io.jooby.Router;
import io.jooby.StatusCode;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowContextgetResponseCode_HttpServerExchangegetStatusCodeFikaTest {

    @Test
    public void test() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        when(exchange.getRequestMethod()).thenReturn(HttpString.tryFromString("GET"));
        when(exchange.getRequestPath()).thenReturn("/test");
        when(exchange.getStatusCode()).thenReturn(200);
        
        UtowContext context = new UtowContext(exchange, router);
        StatusCode result = context.getResponseCode();
    }
}
