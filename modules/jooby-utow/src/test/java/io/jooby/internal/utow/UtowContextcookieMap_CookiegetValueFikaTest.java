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

public class UtowContextcookieMap_CookiegetValueFikaTest {

    @Test
    public void cookieMap_callsCookieGetValue() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        HttpString requestMethod = Mockito.mock(HttpString.class);
        Mockito.when(requestMethod.toString()).thenReturn("GET");
        Mockito.when(exchange.getRequestMethod()).thenReturn(requestMethod);
        Mockito.when(exchange.getRequestPath()).thenReturn("/");
        
        HeaderMap requestHeaders = Mockito.mock(HeaderMap.class);
        Mockito.when(exchange.getRequestHeaders()).thenReturn(requestHeaders);
        
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie cookie = Mockito.mock(Cookie.class);
        Mockito.when(cookie.getName()).thenReturn("testCookie");
        Mockito.when(cookie.getValue()).thenReturn("testValue");
        cookieMap.put("testCookie", cookie);
        
        Mockito.when(exchange.getRequestCookies()).thenReturn(cookieMap);
        
        UtowContext context = new UtowContext(exchange, router);
        context.cookieMap();
    }
}
