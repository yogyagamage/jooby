package io.jooby.internal.jetty;

import org.eclipse.jetty.http.MultiPartFormInputStream;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.doNothing;

public class JettyFileUploaddestroy_MultiPartcleanUpFikaTest {

    @Test
    public void testDestroyCallsCleanUp() throws IOException {
        MultiPartFormInputStream.MultiPart mockMultiPart = Mockito.mock(MultiPartFormInputStream.MultiPart.class);
        doNothing().when(mockMultiPart).cleanUp();
        doNothing().when(mockMultiPart).delete();
        
        JettyFileUpload jettyFileUpload = new JettyFileUpload(mockMultiPart);
        jettyFileUpload.destroy();
    }
}
