package io.jooby.internal.utow;

import io.jooby.Router;
import io.jooby.WebSocket;
import io.undertow.server.HttpServerExchange;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UtowContextupgrade_WebSocketProtocolHandshakeHandlerhandleRequestFikaTest {

    @Test
    public void testUpgradeCallsHandleRequest() {
        HttpServerExchange exchange = mock(HttpServerExchange.class);
        Router router = mock(Router.class);
        
        when(exchange.getRequestHeaders()).thenReturn(new io.undertow.util.HeaderMap());
        when(exchange.getRequestMethod()).thenReturn(new io.undertow.util.HttpString("GET"));
        when(exchange.getRequestPath()).thenReturn("/");
        
        UtowContext context = new UtowContext(exchange, router);
        WebSocket.Initializer initializer = Mockito.mock(WebSocket.Initializer.class);
        
        context.upgrade(initializer);
    }
}
