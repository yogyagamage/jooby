package io.jooby.internal.netty;

import io.jooby.Sender;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpContent;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NettySenderwrite_DefaultHttpContentmethodFikaTest {

    @Test
    void testWriteTriggersDefaultHttpContentConstructor() {
        NettyContext ctx = Mockito.mock(NettyContext.class);
        ChannelHandlerContext context = Mockito.mock(ChannelHandlerContext.class);
        ChannelFuture channelFuture = Mockito.mock(ChannelFuture.class);
        
        Mockito.when(context.writeAndFlush(Mockito.any(DefaultHttpContent.class)))
               .thenReturn(channelFuture);
        
        NettySender sender = new NettySender(ctx, context);
        byte[] data = new byte[]{1, 2, 3};
        Sender.Callback callback = Mockito.mock(Sender.Callback.class);
        
        sender.write(data, callback);
    }
}
