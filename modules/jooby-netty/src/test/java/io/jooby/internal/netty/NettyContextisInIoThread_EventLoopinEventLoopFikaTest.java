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

public class NettyContextisInIoThread_EventLoopinEventLoopFikaTest {

    @Test
    public void testIsInIoThreadCallsInEventLoop() {
        // Create mock ChannelHandlerContext
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        
        // Create mock Channel
        Channel channel = Mockito.mock(Channel.class);
        when(ctx.channel()).thenReturn(channel);
        
        // Create mock EventLoop
        EventLoop eventLoop = Mockito.mock(EventLoop.class);
        when(channel.eventLoop()).thenReturn(eventLoop);
        
        // Create HttpRequest with non-null method
        HttpRequest req = new DefaultFullHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.GET, 
            "/test"
        );
        
        // Create mock Router
        Router router = Mockito.mock(Router.class);
        
        // Create NettyContext instance using the constructor
        NettyContext nettyContext = new NettyContext(
            ctx, 
            req, 
            router, 
            "/test", 
            8192
        );
        
        // Call the entry point method
        nettyContext.isInIoThread();
    }
}
