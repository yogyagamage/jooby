package io.jooby.internal.jetty;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.jooby.Router;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;

import static org.mockito.Mockito.when;

public class JettyContextgetResponseCode_ResponsegetStatusFikaTest {

    @Test
    public void testGetResponseCodeCallsGetStatus() {
        Request mockRequest = Mockito.mock(Request.class);
        Response mockResponse = Mockito.mock(Response.class);
        Router mockRouter = Mockito.mock(Router.class);
        
        when(mockRequest.getMethod()).thenReturn("GET");
        when(mockRequest.getResponse()).thenReturn(mockResponse);
        
        JettyContext context = new JettyContext(mockRequest, mockRouter, 8192, 1000000L);
        
        context.getResponseCode();
    }
}
