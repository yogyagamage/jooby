package io.jooby.internal.netty;

import io.jooby.Sender;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.http.DefaultHttpContent;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class NettySenderwrite_UnpooledwrappedBufferFikaTest {

    @Test
    public void testWriteCallsUnpooledWrappedBuffer() {
        NettyContext mockNettyContext = Mockito.mock(NettyContext.class);
        ChannelHandlerContext mockChannelHandlerContext = Mockito.mock(ChannelHandlerContext.class);
        ChannelFuture mockChannelFuture = Mockito.mock(ChannelFuture.class);
        
        when(mockChannelHandlerContext.writeAndFlush(any(DefaultHttpContent.class)))
            .thenReturn(mockChannelFuture);
        when(mockChannelFuture.addListener(any())).thenReturn(mockChannelFuture);
        
        NettySender sender = new NettySender(mockNettyContext, mockChannelHandlerContext);
        byte[] data = new byte[]{1, 2, 3};
        Sender.Callback callback = Mockito.mock(Sender.Callback.class);
        
        sender.write(data, callback);
    }
}
