package io.jooby.internal.jetty;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.server.HttpOutput;
import io.jooby.Router;
import java.nio.ByteBuffer;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JettyContextsend_ResponsegetHttpOutputFikaTest2 {

    @Test
    public void testSendTriggersGetHttpOutput(
            @Mock Request request,
            @Mock Response response,
            @Mock Router router,
            @Mock HttpOutput httpOutput) throws Exception {
        
        when(request.getMethod()).thenReturn("GET");
        when(request.getResponse()).thenReturn(response);
        when(response.getHttpOutput()).thenReturn(httpOutput);
        
        JettyContext jettyContext = new JettyContext(request, router, 8192, 1000000L);
        
        ByteBuffer buffer = ByteBuffer.wrap("test".getBytes());
        jettyContext.send(buffer);
    }
}
