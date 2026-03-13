package io.jooby.internal.jetty;

import org.eclipse.jetty.http.MultiPartFormInputStream;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class JettyFileUploaddestroy_MultiPartdeleteFikaTest {

    @Test
    public void testDestroy() throws Exception {
        MultiPartFormInputStream.MultiPart multiPart = Mockito.mock(
            MultiPartFormInputStream.MultiPart.class,
            Mockito.withSettings()
                .extraInterfaces(Part.class)
        );
        
        JettyFileUpload jettyFileUpload = new JettyFileUpload(multiPart);
        jettyFileUpload.destroy();
    }
}
