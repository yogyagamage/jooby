package io.jooby.internal.netty;

import io.jooby.Sender;
import io.jooby.Sender.Callback;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundInvoker;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NettySenderclose_ChannelFutureaddListenerFikaTest {

    @Test
    void testCloseInvokesAddListener() {
        NettyContext ctx = Mockito.mock(NettyContext.class);
        ChannelHandlerContext context = Mockito.mock(ChannelHandlerContext.class);
        ChannelFuture channelFuture = Mockito.mock(ChannelFuture.class);
        
        Mockito.when(context.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT))
               .thenReturn(channelFuture);
        
        NettySender sender = new NettySender(ctx, context);
        sender.close();
    }
}
