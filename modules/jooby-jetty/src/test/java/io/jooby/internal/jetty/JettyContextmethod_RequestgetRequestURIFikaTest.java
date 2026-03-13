package io.jooby.internal.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class JettyContextmethod_RequestgetRequestURIFikaTest {

    @Test
    public void test() {
        Request mockRequest = mock(Request.class);
        Response mockResponse = mock(Response.class);
        Router mockRouter = mock(Router.class);
        
        when(mockRequest.getResponse()).thenReturn(mockResponse);
        when(mockRequest.getMethod()).thenReturn("GET");
        when(mockRequest.getRequestURI()).thenReturn("/test");
        
        new JettyContext(mockRequest, mockRouter, 8192, 1000000L);
    }
}
