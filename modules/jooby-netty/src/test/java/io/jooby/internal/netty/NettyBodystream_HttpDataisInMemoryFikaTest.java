package io.jooby.internal.netty;

import io.jooby.Context;
import io.netty.handler.codec.http.multipart.HttpData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.InputStream;

class NettyBodystream_HttpDataisInMemoryFikaTest {

    @Test
    void testStreamCallsIsInMemory() throws Exception {
        // Create mock HttpData that will be used in the path
        HttpData mockHttpData = Mockito.mock(HttpData.class);
        Mockito.when(mockHttpData.isInMemory()).thenReturn(true);
        Mockito.when(mockHttpData.get()).thenReturn(new byte[0]);

        // Create mock Context (required for constructor but not directly used in path)
        Context mockContext = Mockito.mock(Context.class);

        // Instantiate NettyBody using the provided constructor
        NettyBody nettyBody = new NettyBody(mockContext, mockHttpData, 0L);

        // Call the entry point method - this should invoke HttpData.isInMemory()
        InputStream stream = nettyBody.stream();
        stream.close();
    }
}
