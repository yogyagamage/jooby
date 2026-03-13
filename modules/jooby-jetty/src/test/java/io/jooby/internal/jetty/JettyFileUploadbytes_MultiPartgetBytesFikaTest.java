package io.jooby.internal.jetty;

import org.eclipse.jetty.http.MultiPartFormInputStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JettyFileUploadbytes_MultiPartgetBytesFikaTest {

    @Test
    void test() throws IOException {
        MultiPartFormInputStream.MultiPart multiPart = mock(MultiPartFormInputStream.MultiPart.class);
        when(multiPart.getBytes()).thenReturn(new byte[0]);
        
        JettyFileUpload jettyFileUpload = new JettyFileUpload(multiPart);
        jettyFileUpload.bytes();
    }
}
