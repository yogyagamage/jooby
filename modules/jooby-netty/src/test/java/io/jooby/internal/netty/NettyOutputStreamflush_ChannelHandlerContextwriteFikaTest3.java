package io.jooby.internal.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundInvoker;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.ReferenceCounted;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NettyOutputStreamflush_ChannelHandlerContextwriteFikaTest3 {

    @Test
    public void test() throws Exception {
        NettyContext nettyContext = mock(NettyContext.class);
        ChannelHandlerContext channelHandlerContext = mock(ChannelHandlerContext.class);
        HttpResponse httpResponse = mock(HttpResponse.class);
        ByteBufAllocator allocator = mock(ByteBufAllocator.class);
        ByteBuf buffer = mock(ByteBuf.class);
        ChannelPromise voidPromise = mock(ChannelPromise.class);
        ChannelFuture channelFuture = mock(ChannelFuture.class);

        when(channelHandlerContext.alloc()).thenReturn(allocator);
        when(allocator.buffer(0, 1024)).thenReturn(buffer);
        when(buffer.readableBytes()).thenReturn(5);
        when(buffer.copy()).thenReturn(buffer);
        when(channelHandlerContext.voidPromise()).thenReturn(voidPromise);
        when(channelHandlerContext.write(Mockito.any())).thenReturn(channelFuture);
        when(channelHandlerContext.writeAndFlush(Mockito.any())).thenReturn(channelFuture);

        NettyOutputStream nettyOutputStream = new NettyOutputStream(
                nettyContext,
                channelHandlerContext,
                1024,
                httpResponse
        );

        nettyOutputStream.flush();
    }
}
