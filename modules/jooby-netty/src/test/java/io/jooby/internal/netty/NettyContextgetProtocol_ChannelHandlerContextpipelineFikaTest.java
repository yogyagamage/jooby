package io.jooby.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyContextgetProtocol_ChannelHandlerContextpipelineFikaTest {

    @Test
    public void testGetProtocolInvokesPipeline() {
        // Create mock ChannelHandlerContext with a pipeline that returns null for "http2"
        ChannelHandlerContext mockCtx = Mockito.mock(ChannelHandlerContext.class);
        ChannelPipeline mockPipeline = Mockito.mock(ChannelPipeline.class);
        when(mockCtx.pipeline()).thenReturn(mockPipeline);
        when(mockPipeline.get("http2")).thenReturn(null);

        // Create HttpRequest with headers to avoid NPE in constructor
        DefaultHttpRequest mockReq = new DefaultHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.GET, 
            "/test"
        );

        // Create mock Router
        Router mockRouter = Mockito.mock(Router.class);

        // Instantiate NettyContext using the provided constructor
        NettyContext nettyContext = new NettyContext(
            mockCtx,
            mockReq,
            mockRouter,
            "/test",
            8192
        );

        // Call the entry point method - this should invoke ctx.pipeline()
        nettyContext.getProtocol();
    }
}
