package io.jooby.internal.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class JettyContextgetRemoteAddress_RequestgetRemoteAddrFikaTest {

    @Test
    public void testGetRemoteAddress() {
        Request mockRequest = Mockito.mock(Request.class);
        Response mockResponse = Mockito.mock(Response.class);
        Router mockRouter = Mockito.mock(Router.class);
        
        when(mockRequest.getMethod()).thenReturn("GET");
        when(mockRequest.getRemoteAddr()).thenReturn("127.0.0.1");
        when(mockRequest.getResponse()).thenReturn(mockResponse);
        
        JettyContext context = new JettyContext(mockRequest, mockRouter, 8192, 1048576L);
        context.getRemoteAddress();
    }
}
