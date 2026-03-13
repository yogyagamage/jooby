package io.jooby.internal.jetty;

import org.eclipse.jetty.http.MultiPartFormInputStream;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JettyFileUploadstream_MultiPartgetInputStreamFikaTest {

    @Test
    void testStreamCallsMultiPartGetInputStream() throws Exception {
        MultiPartFormInputStream.MultiPart multiPart = mock(MultiPartFormInputStream.MultiPart.class);
        InputStream mockInputStream = mock(InputStream.class);
        when(multiPart.getInputStream()).thenReturn(mockInputStream);

        JettyFileUpload jettyFileUpload = new JettyFileUpload(multiPart);
        jettyFileUpload.stream();
    }
}
