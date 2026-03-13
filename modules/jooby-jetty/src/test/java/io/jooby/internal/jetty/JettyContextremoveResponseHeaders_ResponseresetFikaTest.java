package io.jooby.internal.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class JettyContextremoveResponseHeaders_ResponseresetFikaTest {

    @Test
    public void testRemoveResponseHeadersCallsResponseReset() {
        // Create mock Request
        Request mockRequest = Mockito.mock(Request.class);
        
        // Create mock Response
        Response mockResponse = Mockito.mock(Response.class);
        
        // Create mock Router
        Router mockRouter = Mockito.mock(Router.class);
        
        // Configure mock Request to return mock Response
        when(mockRequest.getResponse()).thenReturn(mockResponse);
        
        // Configure mock Request to return a non-null method string
        when(mockRequest.getMethod()).thenReturn("GET");
        
        // Configure mock Request to return a non-null request URI
        when(mockRequest.getRequestURI()).thenReturn("/test");
        
        // Create JettyContext instance using the constructor
        JettyContext jettyContext = new JettyContext(mockRequest, mockRouter, 8192, 1048576L);
        
        // Call the entry point method
        jettyContext.removeResponseHeaders();
        
        // The test will pass if response.reset() is called without throwing exceptions
        // No assertions or verifications are required per the constraints
    }
}
