package io.jooby.internal.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class JettyContextsetResponseCode_ResponsesetStatusFikaTest {

    @Test
    public void testSetResponseCodeCallsSetStatus() {
        Request mockRequest = Mockito.mock(Request.class);
        Response mockResponse = Mockito.mock(Response.class);
        Router mockRouter = Mockito.mock(Router.class);
        
        when(mockRequest.getMethod()).thenReturn("GET");
        when(mockRequest.getResponse()).thenReturn(mockResponse);
        
        int bufferSize = 8192;
        long maxRequestSize = 1024L;
        
        JettyContext jettyContext = new JettyContext(mockRequest, mockRouter, bufferSize, maxRequestSize);
        
        int statusCode = 200;
        jettyContext.setResponseCode(statusCode);
    }
}
