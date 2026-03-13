package io.jooby.internal.jetty;

import io.jooby.Context;
import io.jooby.Sender;
import org.eclipse.jetty.server.HttpOutput;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

class JettySenderwrite_HttpOutputwriteFikaTest {

    @Test
    void testWrite() throws IOException {
        JettyContext ctx = Mockito.mock(JettyContext.class);
        HttpOutput sender = Mockito.mock(HttpOutput.class);
        JettySender jettySender = new JettySender(ctx, sender);
        
        byte[] data = new byte[]{1, 2, 3};
        Sender.Callback callback = new Sender.Callback() {
            public void onComplete(Context context, Throwable failure) {
                // No-op implementation
            }
        };
        
        jettySender.write(data, callback);
    }
}
