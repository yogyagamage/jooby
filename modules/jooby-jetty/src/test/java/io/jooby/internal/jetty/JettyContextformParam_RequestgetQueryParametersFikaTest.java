package io.jooby.internal.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.MultiMap;
import io.jooby.Formdata;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import java.util.Enumeration;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JettyContextformParam_RequestgetQueryParametersFikaTest {

    @Test
    public void testFormTriggersGetQueryParameters() throws Exception {
        Request mockRequest = mock(Request.class);
        Response mockResponse = mock(Response.class);
        Router mockRouter = mock(Router.class);
        
        when(mockRequest.getResponse()).thenReturn(mockResponse);
        when(mockRequest.getMethod()).thenReturn("GET");
        when(mockRequest.getRequestURI()).thenReturn("/test");
        
        Enumeration<String> emptyEnumeration = Collections.emptyEnumeration();
        when(mockRequest.getParameterNames()).thenReturn(emptyEnumeration);
        
        MultiMap<String> mockQueryParams = new MultiMap<>();
        when(mockRequest.getQueryParameters()).thenReturn(mockQueryParams);
        
        JettyContext context = new JettyContext(mockRequest, mockRouter, 8192, 1000000L);
        
        context.form();
    }
}
