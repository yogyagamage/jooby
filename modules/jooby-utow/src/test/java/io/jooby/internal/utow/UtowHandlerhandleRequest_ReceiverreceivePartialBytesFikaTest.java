package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class UtowHandlerhandleRequest_ReceiverreceivePartialBytesFikaTest {

    @Test
    public void testHandleRequestInvokesReceivePartialBytes() throws Exception {
        Router router = mock(Router.class);
        Router.Match match = mock(Router.Match.class);
        when(router.match(any())).thenReturn(match);
        
        HttpServerExchange exchange = mock(HttpServerExchange.class);
        HeaderMap requestHeaders = new HeaderMap();
        requestHeaders.put(Headers.CONTENT_LENGTH, "1000000");
        when(exchange.getRequestHeaders()).thenReturn(requestHeaders);
        
        HeaderMap responseHeaders = new HeaderMap();
        when(exchange.getResponseHeaders()).thenReturn(responseHeaders);
        
        io.undertow.io.Receiver receiver = mock(io.undertow.io.Receiver.class);
        when(exchange.getRequestReceiver()).thenReturn(receiver);
        
        io.undertow.server.handlers.form.FormDataParser parser = null;
        when(mock(io.undertow.server.handlers.form.FormParserFactory.class)
            .createParser(exchange)).thenReturn(parser);
        
        io.undertow.util.HttpString httpString = mock(io.undertow.util.HttpString.class);
        when(httpString.toString()).thenReturn("POST");
        when(exchange.getRequestMethod()).thenReturn(httpString);
        
        UtowHandler handler = new UtowHandler(router, 8192, 10000000L, false);
        handler.handleRequest(exchange);
    }
}
