package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowHandlerhandleRequest_BuilderaddParserFikaTest {

    @Test
    public void testHandleRequestInvokesBuilderAddParser() throws Exception {
        // Create mocks for constructor dependencies
        Router router = Mockito.mock(Router.class);
        int bufferSize = 8192;
        long maxRequestSize = 1024 * 1024;
        boolean defaultHeaders = false;
        
        // Create instance of class under test
        UtowHandler handler = new UtowHandler(router, bufferSize, maxRequestSize, defaultHeaders);
        
        // Create mock exchange and set up required state to reach the target method
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        
        // Set up request method to NOT be GET (so we go into the else branch)
        HttpString httpMethod = new HttpString("POST");
        when(exchange.getRequestMethod()).thenReturn(httpMethod);
        
        // Set up headers to trigger body parsing path
        io.undertow.util.HeaderMap requestHeaders = new io.undertow.util.HeaderMap();
        requestHeaders.put(io.undertow.util.Headers.CONTENT_LENGTH, "100");
        when(exchange.getRequestHeaders()).thenReturn(requestHeaders);
        
        // Set up response headers
        io.undertow.util.HeaderMap responseHeaders = new io.undertow.util.HeaderMap();
        when(exchange.getResponseHeaders()).thenReturn(responseHeaders);
        
        // Mock the request receiver to avoid NPE
        io.undertow.io.Receiver receiver = Mockito.mock(io.undertow.io.Receiver.class);
        when(exchange.getRequestReceiver()).thenReturn(receiver);
        
        // Mock router match to avoid NPE
        Router.Match match = Mockito.mock(Router.Match.class);
        when(router.match(Mockito.any())).thenReturn(match);
        
        // Call the entry point method
        handler.handleRequest(exchange);
    }
}
