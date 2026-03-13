package io.jooby.internal.jetty;

import io.jooby.Cookie;
import io.jooby.Router;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.ByteBuffer;

public class JettyContextsetResponseCookie_ResponsesetHeaderFikaTest {

    @Test
    public void testSetResponseCookieTriggersSetHeader() {
        Request mockRequest = Mockito.mock(Request.class);
        Response mockResponse = Mockito.mock(Response.class);
        Router mockRouter = Mockito.mock(Router.class);
        
        Mockito.when(mockRequest.getResponse()).thenReturn(mockResponse);
        Mockito.when(mockRequest.getMethod()).thenReturn("GET");
        Mockito.when(mockRequest.getRequestURI()).thenReturn("/test");
        
        JettyContext context = new JettyContext(mockRequest, mockRouter, 8192, 1000000L);
        
        Cookie cookie = new Cookie("testCookie", "testValue");
        context.setResponseCookie(cookie);
    }
}
