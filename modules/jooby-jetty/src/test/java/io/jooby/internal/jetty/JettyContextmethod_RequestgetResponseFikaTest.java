package io.jooby.internal.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JettyContextmethod_RequestgetResponseFikaTest {

    @Test
    public void testRequestGetResponseInvocation() {
        Request mockRequest = mock(Request.class);
        Router mockRouter = mock(Router.class);
        Response mockResponse = mock(Response.class);
        
        when(mockRequest.getResponse()).thenReturn(mockResponse);
        when(mockRequest.getMethod()).thenReturn("GET");
        when(mockRequest.getRequestURI()).thenReturn("/test");
        
        new JettyContext(mockRequest, mockRouter, 8192, 1000000L);
    }
}
