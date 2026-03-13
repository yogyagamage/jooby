package io.jooby.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NettyContextgetScheme_ChannelPipelinegetFikaTest {

    @Test
    public void testGetSchemeInvokesChannelPipelineGet() {
        // Create mock ChannelHandlerContext
        ChannelHandlerContext mockCtx = mock(ChannelHandlerContext.class);
        
        // Create mock ChannelPipeline
        ChannelPipeline mockPipeline = mock(ChannelPipeline.class);
        when(mockCtx.pipeline()).thenReturn(mockPipeline);
        
        // Configure pipeline.get("ssl") to return null (to follow the "http" path)
        when(mockPipeline.get("ssl")).thenReturn(null);
        
        // Create a real HttpRequest with proper headers
        HttpRequest req = new DefaultHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.GET, 
            "/test"
        );
        
        // Create mock Router
        Router mockRouter = mock(Router.class);
        
        // Create NettyContext instance using the constructor
        NettyContext nettyContext = new NettyContext(
            mockCtx,
            req,
            mockRouter,
            "/test",
            8192
        );
        
        // Call the entry point method
        nettyContext.getScheme();
    }
}
