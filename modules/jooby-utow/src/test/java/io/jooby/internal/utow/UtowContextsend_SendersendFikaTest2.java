package io.jooby.internal.utow;

import io.jooby.Router;
import io.jooby.StatusCode;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowContextsend_SendersendFikaTest2 {

    @Test
    public void testSendStatusCode() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        HttpString requestMethod = Mockito.mock(HttpString.class);
        when(requestMethod.toString()).thenReturn("GET");
        when(exchange.getRequestMethod()).thenReturn(requestMethod);
        
        io.undertow.io.Sender sender = Mockito.mock(io.undertow.io.Sender.class);
        when(exchange.getResponseSender()).thenReturn(sender);
        
        UtowContext context = new UtowContext(exchange, router);
        context.send(StatusCode.OK);
    }
}
