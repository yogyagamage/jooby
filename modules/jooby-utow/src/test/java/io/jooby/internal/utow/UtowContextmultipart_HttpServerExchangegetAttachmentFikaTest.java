package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.AttachmentKey;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UtowContextmultipart_HttpServerExchangegetAttachmentFikaTest {

    @Test
    public void test() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        Mockito.when(exchange.getRequestMethod()).thenReturn(HttpString.tryFromString("GET"));
        Mockito.when(exchange.getRequestPath()).thenReturn("/test");
        
        UtowContext context = new UtowContext(exchange, router);
        context.multipart();
    }
}
