package io.jooby.internal.utow;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UtowContextsetResponseCode_HttpServerExchangesetStatusCodeFikaTest {

    @Test
    public void test() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Mockito.when(exchange.getRequestMethod()).thenReturn(HttpString.tryFromString("GET"));
        Mockito.when(exchange.getRequestPath()).thenReturn("/test");
        
        Router router = Mockito.mock(Router.class);
        
        UtowContext context = new UtowContext(exchange, router);
        context.setResponseCode(200);
    }
}
