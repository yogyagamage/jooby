package io.jooby.internal.jetty;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import io.jooby.Router;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JettyContextifStartAsync_RequestisAsyncStartedFikaTest {

    @Mock
    private Request mockRequest;

    @Mock
    private Response mockResponse;

    @Mock
    private Router mockRouter;

    @Test
    public void testResponseSenderTriggersIsAsyncStarted() {
        when(mockRequest.getResponse()).thenReturn(mockResponse);
        when(mockRequest.getMethod()).thenReturn("GET");
        when(mockRequest.getRequestURI()).thenReturn("/test");

        JettyContext context = new JettyContext(
            mockRequest,
            mockRouter,
            8192,
            1000000L
        );

        context.responseSender();
    }
}
