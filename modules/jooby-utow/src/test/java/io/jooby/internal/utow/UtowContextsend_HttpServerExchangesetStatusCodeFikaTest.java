package io.jooby.internal.utow;

import io.jooby.Router;
import io.jooby.StatusCode;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowContextsend_HttpServerExchangesetStatusCodeFikaTest {

    @Test
    public void testSendStatusCode() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        when(exchange.getRequestMethod()).thenReturn(HttpString.tryFromString("GET"));
        when(exchange.getRequestPath()).thenReturn("/test");
        when(exchange.getResponseSender()).thenReturn(Mockito.mock(io.undertow.io.Sender.class));
        
        UtowContext context = new UtowContext(exchange, router);
        
        context.send(StatusCode.OK);
    }
}
