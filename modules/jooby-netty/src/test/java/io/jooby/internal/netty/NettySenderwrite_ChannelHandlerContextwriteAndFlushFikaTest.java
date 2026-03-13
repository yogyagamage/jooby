package io.jooby.internal.netty;

import io.jooby.Sender;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpContent;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettySenderwrite_ChannelHandlerContextwriteAndFlushFikaTest {

    @Test
    public void testWriteCallsWriteAndFlush() {
        NettyContext nettyContext = Mockito.mock(NettyContext.class);
        ChannelHandlerContext channelHandlerContext = Mockito.mock(ChannelHandlerContext.class);
        
        ChannelFuture channelFuture = Mockito.mock(ChannelFuture.class);
        when(channelHandlerContext.writeAndFlush(Mockito.any(DefaultHttpContent.class)))
            .thenReturn(channelFuture);
        
        NettySender nettySender = new NettySender(nettyContext, channelHandlerContext);
        byte[] data = new byte[]{1, 2, 3};
        Sender.Callback callback = Mockito.mock(Sender.Callback.class);
        
        nettySender.write(data, callback);
    }
}
