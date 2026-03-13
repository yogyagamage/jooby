package io.jooby.internal.jetty;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.jooby.Router;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.server.HttpOutput;
import javax.servlet.AsyncContext;
import java.nio.ByteBuffer;

class JettyContextsend_ResponsegetContentLengthFikaTest2 {

    @Test
    void testSendCallsGetContentLength() throws Exception {
        // Mock Request
        Request mockRequest = Mockito.mock(Request.class);
        Response mockResponse = Mockito.mock(Response.class);
        Router mockRouter = Mockito.mock(Router.class);
        HttpOutput mockHttpOutput = Mockito.mock(HttpOutput.class);
        AsyncContext mockAsyncContext = Mockito.mock(AsyncContext.class);
        
        // Setup request method to avoid NPE in constructor
        Mockito.when(mockRequest.getMethod()).thenReturn("GET");
        Mockito.when(mockRequest.getResponse()).thenReturn(mockResponse);
        Mockito.when(mockRequest.isAsyncStarted()).thenReturn(false);
        Mockito.when(mockRequest.startAsync()).thenReturn(mockAsyncContext);
        Mockito.when(mockRequest.getAsyncContext()).thenReturn(mockAsyncContext);
        
        // Setup response
        Mockito.when(mockResponse.getContentLength()).thenReturn(0L);
        Mockito.when(mockResponse.getHttpOutput()).thenReturn(mockHttpOutput);
        
        // Create instance using constructor
        JettyContext context = new JettyContext(mockRequest, mockRouter, 8192, 1000000L);
        
        // Create ByteBuffer array
        ByteBuffer[] buffers = new ByteBuffer[]{ByteBuffer.wrap(new byte[]{1, 2, 3})};
        
        // Call the entry point method
        context.send(buffers);
    }
}
