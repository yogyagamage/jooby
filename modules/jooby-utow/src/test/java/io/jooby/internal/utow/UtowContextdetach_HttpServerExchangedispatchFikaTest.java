package io.jooby.internal.utow;

import io.jooby.Route;
import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import io.undertow.util.SameThreadExecutor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UtowContextdetach_HttpServerExchangedispatchFikaTest {

    @Test
    public void testDetachCallsDispatch() throws Exception {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        Mockito.when(exchange.getRequestMethod()).thenReturn(HttpString.tryFromString("GET"));
        Mockito.when(exchange.getRequestPath()).thenReturn("/test");
        
        UtowContext context = new UtowContext(exchange, router);
        
        Route.Handler handler = Mockito.mock(Route.Handler.class);
        
        context.detach(handler);
    }
}
