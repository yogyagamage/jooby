package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.Executor;

public class UtowContextdispatch_HttpServerExchangedispatchFikaTest {

    @Test
    public void testDispatch() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Mockito.when(exchange.getRequestMethod()).thenReturn(HttpString.tryFromString("GET"));
        Mockito.when(exchange.getRequestPath()).thenReturn("/test");
        
        Router router = Mockito.mock(Router.class);
        
        UtowContext context = new UtowContext(exchange, router);
        
        Executor executor = Runnable::run;
        Runnable action = () -> {};
        
        context.dispatch(executor, action);
    }
}
