package io.jooby.internal.jetty;

import io.jooby.Cookie;
import io.jooby.Router;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class JettyContextsetResponseCookie_ResponseaddHeaderFikaTest {

    @Test
    public void testSetResponseCookieCallsAddHeader() {
        Request mockRequest = Mockito.mock(Request.class);
        Response mockResponse = Mockito.mock(Response.class);
        Router mockRouter = Mockito.mock(Router.class);
        
        when(mockRequest.getMethod()).thenReturn("GET");
        when(mockRequest.getRequestURI()).thenReturn("/test");
        when(mockRequest.getResponse()).thenReturn(mockResponse);
        
        JettyContext jettyContext = new JettyContext(mockRequest, mockRouter, 8192, 1000000L);
        
        Cookie cookie = new Cookie("testCookie", "testValue");
        jettyContext.setResponseCookie(cookie);
    }
}
