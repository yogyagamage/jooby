package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.when;

public class UtowHandlerhandleRequest_ReceiverreceiveFullBytesFikaTest {

    @Test
    public void testHandleRequestInvokesReceiveFullBytes() throws Exception {
        Router router = Mockito.mock(Router.class);
        Router.Match match = Mockito.mock(Router.Match.class);
        when(router.match(Mockito.any())).thenReturn(match);

        UtowHandler handler = new UtowHandler(router, 8192, 8192L, false);

        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        HeaderMap requestHeaders = new HeaderMap();
        requestHeaders.put(Headers.CONTENT_LENGTH, "100");
        when(exchange.getRequestHeaders()).thenReturn(requestHeaders);

        HeaderMap responseHeaders = new HeaderMap();
        when(exchange.getResponseHeaders()).thenReturn(responseHeaders);

        io.undertow.io.Receiver receiver = Mockito.mock(io.undertow.io.Receiver.class);
        when(exchange.getRequestReceiver()).thenReturn(receiver);

        when(exchange.getRequestMethod()).thenReturn(io.undertow.util.HttpString.tryFromString("POST"));

        handler.handleRequest(exchange);
    }
}
