package io.jooby.internal.utow;

import io.jooby.Route;
import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowContextonComplete_HttpServerExchangeaddExchangeCompleteListenerFikaTest {

    @Test
    public void testOnCompletePath() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        when(exchange.getRequestMethod()).thenReturn(HttpString.tryFromString("GET"));
        when(exchange.getRequestPath()).thenReturn("/test");
        
        UtowContext context = new UtowContext(exchange, router);
        Route.Complete task = Mockito.mock(Route.Complete.class);
        
        context.onComplete(task);
    }
}
