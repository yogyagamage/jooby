package io.jooby.internal.jetty;

import io.jooby.Formdata;
import io.jooby.Router;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.MultiMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Enumeration;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JettyContextformParam_RequestgetParameterNamesFikaTest {

    @Mock
    private Request mockRequest;

    @Mock
    private Response mockResponse;

    @Mock
    private Router mockRouter;

    @Test
    void testFormTriggersGetParameterNames() {
        // Setup mock request to return empty enumeration for getParameterNames
        when(mockRequest.getParameterNames()).thenReturn(Collections.emptyEnumeration());
        when(mockRequest.getQueryParameters()).thenReturn(new MultiMap<>());
        when(mockRequest.getMethod()).thenReturn("GET");
        when(mockRequest.getRequestURI()).thenReturn("/test");
        when(mockRequest.getResponse()).thenReturn(mockResponse);

        // Create instance using the provided constructor
        JettyContext context = new JettyContext(mockRequest, mockRouter, 8192, 1000000L);

        // Trigger the entry point method which should call formParam -> request.getParameterNames()
        context.form();
    }
}
