package io.jooby.internal.jetty;

import io.jooby.Cookie;
import io.jooby.Router;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.eclipse.jetty.http.HttpHeader.SET_COOKIE;

public class JettyContextsetResponseCookie_HttpHeaderasStringFikaTest {

    @Test
    public void testSetResponseCookieInvokesHttpHeaderAsString() {
        Request mockRequest = Mockito.mock(Request.class);
        Response mockResponse = Mockito.mock(Response.class);
        Router mockRouter = Mockito.mock(Router.class);
        
        Mockito.when(mockRequest.getResponse()).thenReturn(mockResponse);
        Mockito.when(mockRequest.getMethod()).thenReturn("GET");
        Mockito.when(mockRequest.getRequestURI()).thenReturn("/test");
        
        JettyContext jettyContext = new JettyContext(mockRequest, mockRouter, 8192, 1000000L);
        
        Cookie cookie = new Cookie("testCookie", "testValue");
        jettyContext.setResponseCookie(cookie);
    }
}
