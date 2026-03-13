package io.jooby.internal.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NettyContextoperationComplete_ChannelFuturechannelFikaTest {

    @Test
    void testOperationCompleteInvokesChannelFutureChannel() throws Exception {
        // Create mock ChannelHandlerContext
        ChannelHandlerContext mockCtx = mock(ChannelHandlerContext.class);
        
        // Create mock HttpRequest with keep-alive false
        HttpRequest mockReq = new DefaultHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.GET, 
            "/test"
        );
        mockReq.headers().set("Connection", "close");
        
        // Create mock Router
        Router mockRouter = mock(Router.class);
        
        // Create mock ChannelFuture with channel
        ChannelFuture mockFuture = mock(ChannelFuture.class);
        Channel mockChannel = mock(Channel.class);
        when(mockFuture.channel()).thenReturn(mockChannel);
        
        // Instantiate NettyContext using provided constructor
        NettyContext context = new NettyContext(
            mockCtx,
            mockReq,
            mockRouter,
            "/test",
            8192
        );
        
        // Call the entry point method
        context.operationComplete(mockFuture);
    }
}
