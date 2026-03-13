package io.jooby.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultChannelPromise;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.HttpMethod;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyContextsend_EventLoopinEventLoopFikaTest {

    @Test
    public void testSendByteArrayArrayTriggersEventLoopInEventLoop() throws Exception {
        // Create mock ChannelHandlerContext
        ChannelHandlerContext mockCtx = Mockito.mock(ChannelHandlerContext.class);
        
        // Create mock Channel
        Channel mockChannel = Mockito.mock(Channel.class);
        
        // Create mock EventLoop that returns true for inEventLoop()
        EventLoop mockEventLoop = Mockito.mock(EventLoop.class);
        when(mockEventLoop.inEventLoop()).thenReturn(true);
        
        // Setup the chain: ctx.channel().eventLoop().inEventLoop()
        when(mockCtx.channel()).thenReturn(mockChannel);
        when(mockChannel.eventLoop()).thenReturn(mockEventLoop);
        
        // Create mock HttpRequest
        HttpRequest mockReq = new DefaultHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.GET, 
            "/test"
        );
        
        // Create mock Router
        Router mockRouter = Mockito.mock(Router.class);
        
        // Create NettyContext instance using the provided constructor
        NettyContext nettyContext = new NettyContext(
            mockCtx,
            mockReq,
            mockRouter,
            "/test",
            8192
        );
        
        // Call the entry point method with some dummy data
        byte[][] data = new byte[][]{"test".getBytes()};
        nettyContext.send(data);
    }
}
