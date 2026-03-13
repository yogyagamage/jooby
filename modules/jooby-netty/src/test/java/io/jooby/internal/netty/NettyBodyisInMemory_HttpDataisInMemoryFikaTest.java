package io.jooby.internal.netty;

import io.jooby.Context;
import io.netty.handler.codec.http.multipart.HttpData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NettyBodyisInMemory_HttpDataisInMemoryFikaTest {

    @Test
    public void testIsInMemory() {
        Context mockContext = Mockito.mock(Context.class);
        HttpData mockHttpData = Mockito.mock(HttpData.class);
        
        NettyBody nettyBody = new NettyBody(mockContext, mockHttpData, 0L);
        
        nettyBody.isInMemory();
    }
}
