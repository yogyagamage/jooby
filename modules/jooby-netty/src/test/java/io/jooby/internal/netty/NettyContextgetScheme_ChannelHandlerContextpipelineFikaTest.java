package io.jooby.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NettyContextgetScheme_ChannelHandlerContextpipelineFikaTest {

    @Test
    public void testGetSchemeInvokesPipeline() {
        // Create mock ChannelHandlerContext with pipeline
        ChannelHandlerContext mockCtx = mock(ChannelHandlerContext.class);
        when(mockCtx.pipeline()).thenReturn(Mockito.mock(io.netty.channel.ChannelPipeline.class));
        
        // Create HttpRequest with non-null method
        HttpRequest mockReq = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/");
        
        // Create mock Router
        Router mockRouter = mock(Router.class);
        
        // Create NettyContext instance using the provided constructor
        NettyContext nettyContext = new NettyContext(
            mockCtx,
            mockReq,
            mockRouter,
            "/test",
            8192
        );
        
        // Call the entry point method
        nettyContext.getScheme();
    }
}
