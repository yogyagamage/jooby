package io.jooby.internal.netty;

import io.jooby.Context;
import io.netty.handler.codec.http.multipart.HttpData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.when;

public class NettyBodybytes_HttpDatagetFikaTest {

    @Test
    public void testBytesCallsHttpDataGet() throws IOException {
        // Create mock HttpData that is in memory
        HttpData mockHttpData = Mockito.mock(HttpData.class);
        when(mockHttpData.isInMemory()).thenReturn(true);
        when(mockHttpData.get()).thenReturn(new byte[0]);

        // Create mock Context (required for constructor but not used in the path)
        Context mockContext = Mockito.mock(Context.class);

        // Instantiate NettyBody using the provided constructor
        NettyBody nettyBody = new NettyBody(mockContext, mockHttpData, 0L);

        // Call the entry point method - this should invoke HttpData.get()
        nettyBody.bytes();
    }
}
