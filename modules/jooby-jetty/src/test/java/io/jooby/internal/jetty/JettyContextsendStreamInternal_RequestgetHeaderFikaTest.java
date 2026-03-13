package io.jooby.internal.jetty;

import io.jooby.ByteRange;
import io.jooby.Router;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

class JettyContextsendStreamInternal_RequestgetHeaderFikaTest {

    @Test
    void testSendStreamInternalTriggersGetHeader() throws Exception {
        // Create mock Request that will return a header value when getHeader is called
        Request mockRequest = Mockito.mock(Request.class);
        Mockito.when(mockRequest.getHeader(HttpHeader.RANGE.asString()))
                .thenReturn("bytes=0-100");
        Mockito.when(mockRequest.getMethod()).thenReturn("GET");
        Mockito.when(mockRequest.getRequestURI()).thenReturn("/test");

        // Create real Response with proper content length
        Response mockResponse = Mockito.mock(Response.class);
        Mockito.when(mockRequest.getResponse()).thenReturn(mockResponse);
        Mockito.when(mockResponse.getContentLength()).thenReturn(200L);
        Mockito.when(mockResponse.getHttpOutput()).thenReturn(Mockito.mock(org.eclipse.jetty.server.HttpOutput.class));

        // Mock Router
        Router mockRouter = Mockito.mock(Router.class);

        // Create JettyContext instance using constructor
        JettyContext context = new JettyContext(mockRequest, mockRouter, 8192, 1000000L);

        // Create a simple ReadableByteChannel
        byte[] data = new byte[50];
        InputStream inputStream = new ByteArrayInputStream(data);
        ReadableByteChannel channel = Channels.newChannel(inputStream);

        // Call the entry point method
        context.send(channel);
    }
}
