package io.jooby.internal.utow;

import io.jooby.WebSocket;
import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowContextupgrade_HandlerswebsocketFikaTest {

    @Test
    public void testUpgradeCallsHandlersWebsocket() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        HttpString requestMethod = Mockito.mock(HttpString.class);
        when(requestMethod.toString()).thenReturn("GET");
        when(exchange.getRequestMethod()).thenReturn(requestMethod);
        when(exchange.getRequestPath()).thenReturn("/test");
        
        UtowContext context = new UtowContext(exchange, router);
        
        WebSocket.Initializer initializer = Mockito.mock(WebSocket.Initializer.class);
        
        context.upgrade(initializer);
    }
}
