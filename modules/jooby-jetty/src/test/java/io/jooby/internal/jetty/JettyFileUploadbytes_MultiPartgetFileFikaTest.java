package io.jooby.internal.jetty;

import org.eclipse.jetty.http.MultiPartFormInputStream;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

public class JettyFileUploadbytes_MultiPartgetFileFikaTest {

    @Test
    public void test() throws IOException {
        MultiPartFormInputStream.MultiPart multiPart = Mockito.mock(MultiPartFormInputStream.MultiPart.class);
        
        Mockito.when(multiPart.getBytes()).thenReturn(null);
        
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();
        Mockito.when(multiPart.getFile()).thenReturn(tempFile);
        
        JettyFileUpload jettyFileUpload = new JettyFileUpload(multiPart);
        jettyFileUpload.bytes();
    }
}
