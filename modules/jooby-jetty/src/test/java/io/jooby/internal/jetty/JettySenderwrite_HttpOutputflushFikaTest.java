package io.jooby.internal.jetty;

import io.jooby.Context;
import io.jooby.Sender;
import org.eclipse.jetty.server.HttpOutput;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

class JettySenderwrite_HttpOutputflushFikaTest {

    @Test
    void testWriteCallsFlush() throws IOException {
        // Create mocks for constructor dependencies
        JettyContext mockCtx = Mockito.mock(JettyContext.class);
        HttpOutput mockHttpOutput = Mockito.mock(HttpOutput.class);
        
        // Create real instance of class under test
        JettySender jettySender = new JettySender(mockCtx, mockHttpOutput);
        
        // Create callback that does nothing
        Sender.Callback callback = new Sender.Callback() {
            public void onComplete(Context context, Throwable failure) {
                // No-op implementation
            }
        };
        
        // Call entry point method
        byte[] data = new byte[]{1, 2, 3};
        jettySender.write(data, callback);
        
        // The test will execute the full chain:
        // JettySender.write() -> HttpOutput.write() -> HttpOutput.flush()
        // No assertions needed as per requirements
    }
}
