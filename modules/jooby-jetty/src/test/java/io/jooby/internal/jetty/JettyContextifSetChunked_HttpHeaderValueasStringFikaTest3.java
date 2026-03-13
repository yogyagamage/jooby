package io.jooby.internal.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class JettyContextifSetChunked_HttpHeaderValueasStringFikaTest3 {

    @Test
    public void testResponseSenderTriggersIfSetChunkedAndHttpHeaderValueAsString() {
        // Create mock Request
        Request mockRequest = Mockito.mock(Request.class);
        
        // Create mock Response
        Response mockResponse = Mockito.mock(Response.class);
        
        // Configure mockRequest to return mockResponse
        when(mockRequest.getResponse()).thenReturn(mockResponse);
        
        // Configure mockRequest to return a non-null method string
        when(mockRequest.getMethod()).thenReturn("GET");
        
        // Configure mockResponse to have content length <= 0 to trigger ifSetChunked
        when(mockResponse.getContentLength()).thenReturn(0L);
        
        // Create mock Router
        Router mockRouter = Mockito.mock(Router.class);
        
        // Instantiate JettyContext using the constructor
        JettyContext jettyContext = new JettyContext(mockRequest, mockRouter, 8192, 1048576L);
        
        // Call the entry point method
        jettyContext.responseSender();
    }
}
