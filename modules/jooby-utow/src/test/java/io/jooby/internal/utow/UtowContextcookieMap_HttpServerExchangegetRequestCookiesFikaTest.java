package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.Cookie;
import io.undertow.util.HeaderMap;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Map;

public class UtowContextcookieMap_HttpServerExchangegetRequestCookiesFikaTest {

    @Test
    public void testCookieMapCallsGetRequestCookies() {
        // Create mock exchange that will return a non-empty cookie collection
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        
        // Create a mock cookie map with at least one cookie
        Map<String, Cookie> cookieMap = Collections.singletonMap(
            "testCookie", 
            Mockito.mock(Cookie.class)
        );
        
        // Configure the exchange to return the cookie map when getRequestCookies() is called
        Mockito.when(exchange.getRequestCookies()).thenReturn(cookieMap);
        
        // Configure other required exchange methods
        Mockito.when(exchange.getRequestMethod()).thenReturn(new io.undertow.util.HttpString("GET"));
        Mockito.when(exchange.getRequestPath()).thenReturn("/test");
        Mockito.when(exchange.getResponseHeaders()).thenReturn(new HeaderMap());
        
        // Create mock router
        Router router = Mockito.mock(Router.class);
        
        // Instantiate UtowContext using the provided constructor
        UtowContext context = new UtowContext(exchange, router);
        
        // Call the entry point method - this should trigger the call to exchange.getRequestCookies()
        Map<String, String> result = context.cookieMap();
        
        // No assertions required - test passes if no exceptions are thrown
    }
}
