package io.jooby.internal.netty;

import io.jooby.Router;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NettyContextoperationComplete_ChannelcloseFikaTest {

    @Test
    public void testOperationCompleteTriggersChannelClose() throws Exception {
        // Create mock ChannelHandlerContext
        ChannelHandlerContext mockCtx = mock(ChannelHandlerContext.class);
        
        // Create mock Channel
        Channel mockChannel = mock(Channel.class);
        when(mockCtx.channel()).thenReturn(mockChannel);
        
        // Create mock ChannelFuture
        ChannelFuture mockFuture = mock(ChannelFuture.class);
        when(mockFuture.channel()).thenReturn(mockChannel);
        when(mockFuture.cause()).thenReturn(null);
        
        // Create HttpRequest that is NOT keep-alive
        HttpRequest req = new DefaultHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.GET, 
            "/test"
        );
        req.headers().set("Connection", "close");
        
        // Create mock Router
        Router mockRouter = mock(Router.class);
        
        // Instantiate NettyContext using the provided constructor
        NettyContext context = new NettyContext(
            mockCtx,
            req,
            mockRouter,
            "/test",
            8192
        );
        
        // Call the entry point method
        context.operationComplete(mockFuture);
        
        // The test will execute the chain:
        // operationComplete -> destroy -> finally block -> future.channel().close()
        // No assertions needed as per requirements
    }
}
