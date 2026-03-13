package io.jooby.internal.jetty;

import org.eclipse.jetty.http.MultiPartFormInputStream;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class JettyFileUploadgetFileName_MultiPartgetSubmittedFileNameFikaTest {

    @Test
    public void testGetFileName() {
        MultiPartFormInputStream.MultiPart multiPart = mock(MultiPartFormInputStream.MultiPart.class);
        JettyFileUpload jettyFileUpload = new JettyFileUpload(multiPart);
        
        jettyFileUpload.getFileName();
    }
}
