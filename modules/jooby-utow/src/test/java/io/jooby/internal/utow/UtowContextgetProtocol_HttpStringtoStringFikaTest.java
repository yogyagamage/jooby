package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowContextgetProtocol_HttpStringtoStringFikaTest {

    @Test
    public void testGetProtocolCallsHttpStringToString() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        HttpString protocolHttpString = Mockito.mock(HttpString.class);
        when(exchange.getProtocol()).thenReturn(protocolHttpString);
        
        HttpString requestMethodHttpString = Mockito.mock(HttpString.class);
        when(requestMethodHttpString.toString()).thenReturn("GET");
        when(exchange.getRequestMethod()).thenReturn(requestMethodHttpString);
        
        UtowContext context = new UtowContext(exchange, router);
        context.getProtocol();
    }
}
