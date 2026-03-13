package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.InetSocketAddress;

public class UtowContextgetRemoteAddress_HttpServerExchangegetSourceAddressFikaTest {

    @Test
    public void testGetRemoteAddressInvokesGetSourceAddress() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        Mockito.when(exchange.getRequestMethod()).thenReturn(HttpString.tryFromString("GET"));
        Mockito.when(exchange.getRequestPath()).thenReturn("/test");
        Mockito.when(exchange.getSourceAddress()).thenReturn(new InetSocketAddress("127.0.0.1", 8080));
        
        UtowContext context = new UtowContext(exchange, router);
        context.getRemoteAddress();
    }
}
