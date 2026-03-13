package io.jooby.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyContextisInIoThread_ChannelHandlerContextchannelFikaTest {

    @Test
    public void test() {
        // Create mock ChannelHandlerContext
        ChannelHandlerContext mockCtx = Mockito.mock(ChannelHandlerContext.class);
        
        // Create mock Channel
        Channel mockChannel = Mockito.mock(Channel.class);
        
        // Create mock EventLoop
        EventLoop mockEventLoop = Mockito.mock(EventLoop.class);
        
        // Setup the chain: ctx.channel().eventLoop().inEventLoop()
        when(mockCtx.channel()).thenReturn(mockChannel);
        when(mockChannel.eventLoop()).thenReturn(mockEventLoop);
        when(mockEventLoop.inEventLoop()).thenReturn(true);
        
        // Create a real HttpRequest with a non-null method
        HttpRequest req = new DefaultFullHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.GET, 
            "/test"
        );
        
        // Create mock Router
        Router mockRouter = Mockito.mock(Router.class);
        
        // Create NettyContext instance using the constructor
        NettyContext nettyContext = new NettyContext(
            mockCtx,
            req,
            mockRouter,
            "/test",
            8192
        );
        
        // Call the entry point method - this should trigger ctx.channel() call
        nettyContext.isInIoThread();
    }
}
