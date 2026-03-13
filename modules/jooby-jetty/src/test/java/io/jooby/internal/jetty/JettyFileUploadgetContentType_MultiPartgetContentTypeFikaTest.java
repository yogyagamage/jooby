package io.jooby.internal.jetty;

import org.junit.jupiter.api.Test;
import org.eclipse.jetty.http.MultiPartFormInputStream;
import org.eclipse.jetty.http.MultiPartFormInputStream.MultiPart;

import java.io.ByteArrayInputStream;
import java.io.IOException;

class JettyFileUploadgetContentType_MultiPartgetContentTypeFikaTest {

    @Test
    void testGetContentType() throws IOException {
        MultiPartFormInputStream multiPartFormInputStream = new MultiPartFormInputStream(
            new ByteArrayInputStream(new byte[0]),
            "boundary",
            null,
            null
        );
        
        MultiPart multiPart = multiPartFormInputStream.new MultiPart(null, null);
        
        JettyFileUpload jettyFileUpload = new JettyFileUpload(multiPart);
        
        jettyFileUpload.getContentType();
    }
}
