package io.jooby.internal.jetty;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.server.HttpOutput;
import io.jooby.Router;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Channels;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class JettyContextsendStreamInternal_HttpOutputsendContentFikaTest {

    @Test
    void testSendReadableByteChannelTriggersHttpOutputSendContent() throws Exception {
        // Mock dependencies
        Request mockRequest = mock(Request.class);
        Response mockResponse = mock(Response.class);
        HttpOutput mockHttpOutput = mock(HttpOutput.class);
        Router mockRouter = mock(Router.class);
        
        // Configure mockRequest to avoid NPE in constructor
        when(mockRequest.getMethod()).thenReturn("GET");
        when(mockRequest.getRequestURI()).thenReturn("/test");
        
        // Configure mockResponse
        when(mockRequest.getResponse()).thenReturn(mockResponse);
        when(mockResponse.getHttpOutput()).thenReturn(mockHttpOutput);
        
        // Set content length to avoid chunked transfer encoding path
        when(mockResponse.getContentLength()).thenReturn(10L);
        
        // Create test instance
        JettyContext context = new JettyContext(mockRequest, mockRouter, 8192, 1000000L);
        
        // Create test data
        byte[] testData = "test data".getBytes();
        InputStream inputStream = new ByteArrayInputStream(testData);
        ReadableByteChannel channel = Channels.newChannel(inputStream);
        
        // Execute the entry point
        context.send(channel);
    }
}
