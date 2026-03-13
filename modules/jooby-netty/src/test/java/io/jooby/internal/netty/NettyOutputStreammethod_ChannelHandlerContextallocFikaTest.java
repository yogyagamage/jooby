package io.jooby.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NettyOutputStreammethod_ChannelHandlerContextallocFikaTest {

    @Test
    public void test() {
        NettyContext nettyContext = Mockito.mock(NettyContext.class);
        ChannelHandlerContext channelHandlerContext = Mockito.mock(ChannelHandlerContext.class);
        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        
        Mockito.when(channelHandlerContext.alloc()).thenReturn(Mockito.mock(io.netty.buffer.ByteBufAllocator.class));
        
        new NettyOutputStream(nettyContext, channelHandlerContext, 1024, httpResponse);
    }
}
