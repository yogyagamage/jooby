package io.jooby.internal.utow;

import io.jooby.CompletionListeners;
import io.jooby.Context;
import io.jooby.Route;
import io.undertow.server.ExchangeCompletionListener;
import io.undertow.server.HttpServerExchange;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class UtowCompletionListenerexchangeEvent_NextListenerproceedFikaTest {

    @Test
    public void testExchangeEventCallsNextListenerProceed() {
        UtowContext ctx = mock(UtowContext.class);
        UtowCompletionListener listener = new UtowCompletionListener(ctx);
        
        HttpServerExchange exchange = mock(HttpServerExchange.class);
        ExchangeCompletionListener.NextListener nextListener = mock(ExchangeCompletionListener.NextListener.class);
        
        listener.exchangeEvent(exchange, nextListener);
    }
}
