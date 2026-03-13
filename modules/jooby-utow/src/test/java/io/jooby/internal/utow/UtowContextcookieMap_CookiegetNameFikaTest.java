package io.jooby.internal.utow;

import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import io.undertow.server.handlers.Cookie;
import io.undertow.util.HeaderMap;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

public class UtowContextcookieMap_CookiegetNameFikaTest {

    @Test
    public void cookieMap_invokesCookieGetName() {
        // Mock HttpServerExchange
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        
        // Setup request method to avoid NPE in constructor
        HttpString requestMethod = Mockito.mock(HttpString.class);
        when(requestMethod.toString()).thenReturn("GET");
        when(exchange.getRequestMethod()).thenReturn(requestMethod);
        
        // Setup request path
        when(exchange.getRequestPath()).thenReturn("/");
        
        // Setup request cookies
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie cookie = Mockito.mock(Cookie.class);
        when(cookie.getName()).thenReturn("testCookie");
        when(cookie.getValue()).thenReturn("testValue");
        cookieMap.put("testCookie", cookie);
        
        HeaderMap requestHeaders = Mockito.mock(HeaderMap.class);
        when(exchange.getRequestCookies()).thenReturn(cookieMap);
        
        // Mock Router
        Router router = Mockito.mock(Router.class);
        
        // Create UtowContext instance
        UtowContext context = new UtowContext(exchange, router);
        
        // Invoke entry point method
        context.cookieMap();
    }
}
