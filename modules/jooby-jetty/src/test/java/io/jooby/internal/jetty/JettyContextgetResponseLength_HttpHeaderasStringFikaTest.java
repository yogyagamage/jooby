package io.jooby.internal.jetty;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import io.jooby.Router;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JettyContextgetResponseLength_HttpHeaderasStringFikaTest {

    @Mock
    private Request mockRequest;

    @Mock
    private Response mockResponse;

    @Mock
    private Router mockRouter;

    @Test
    public void testGetResponseLength() {
        when(mockRequest.getMethod()).thenReturn("GET");
        when(mockRequest.getResponse()).thenReturn(mockResponse);
        when(mockResponse.getContentLength()).thenReturn(-1L);
        when(mockResponse.getHeader("Content-Length")).thenReturn("123");

        JettyContext context = new JettyContext(mockRequest, mockRouter, 8192, 1000000L);
        context.getResponseLength();
    }
}
