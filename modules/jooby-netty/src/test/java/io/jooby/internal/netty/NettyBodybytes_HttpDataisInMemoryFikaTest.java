package io.jooby.internal.netty;

import io.jooby.Context;
import io.netty.handler.codec.http.multipart.HttpData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyBodybytes_HttpDataisInMemoryFikaTest {

    @Test
    public void test() throws Exception {
        Context mockCtx = Mockito.mock(Context.class);
        HttpData mockData = Mockito.mock(HttpData.class);
        
        when(mockData.isInMemory()).thenReturn(true);
        when(mockData.get()).thenReturn(new byte[0]);
        
        NettyBody nettyBody = new NettyBody(mockCtx, mockData, 0L);
        nettyBody.bytes();
    }
}
