package io.jooby.internal.utow;

import io.jooby.Cookie;
import io.jooby.Router;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.undertow.util.Headers.SET_COOKIE;

public class UtowContextsetResponseCookie_HeaderMapaddFikaTest {

    @Test
    public void testSetResponseCookieInvokesHeaderMapAdd() {
        HttpServerExchange exchange = Mockito.mock(HttpServerExchange.class);
        Router router = Mockito.mock(Router.class);
        
        HttpString requestMethod = HttpString.tryFromString("GET");
        Mockito.when(exchange.getRequestMethod()).thenReturn(requestMethod);
        
        HeaderMap responseHeaders = new HeaderMap();
        Mockito.when(exchange.getResponseHeaders()).thenReturn(responseHeaders);
        
        UtowContext context = new UtowContext(exchange, router);
        
        Cookie cookie = new Cookie("testCookie", "testValue");
        context.setResponseCookie(cookie);
    }
}
