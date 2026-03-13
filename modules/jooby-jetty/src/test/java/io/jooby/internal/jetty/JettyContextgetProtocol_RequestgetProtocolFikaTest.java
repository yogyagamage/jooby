package io.jooby.internal.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JettyContextgetProtocol_RequestgetProtocolFikaTest {

    @Test
    public void testGetProtocol() {
        Request mockRequest = mock(Request.class);
        Response mockResponse = mock(Response.class);
        Router mockRouter = mock(Router.class);
        
        when(mockRequest.getResponse()).thenReturn(mockResponse);
        when(mockRequest.getMethod()).thenReturn("GET");
        when(mockRequest.getRequestURI()).thenReturn("/test");
        
        JettyContext context = new JettyContext(mockRequest, mockRouter, 8192, 1000000L);
        
        context.getProtocol();
    }
}
