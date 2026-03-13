package io.jooby.internal.jetty;

import org.eclipse.jetty.http.MultiPartFormInputStream;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class JettyFileUploadgetName_MultiPartgetNameFikaTest {

    @Test
    void testGetName() {
        MultiPartFormInputStream.MultiPart multiPart = mock(MultiPartFormInputStream.MultiPart.class);
        JettyFileUpload jettyFileUpload = new JettyFileUpload(multiPart);
        jettyFileUpload.getName();
    }
}
