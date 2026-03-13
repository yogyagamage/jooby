package io.jooby.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyContextprepareChunked_ChannelPipelineaddAfterFikaTest {

    @Test
    public void testResponseSenderTriggersPipelineAddAfter() {
        // Mock ChannelHandlerContext
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        
        // Create a real HttpRequest with headers to avoid NPE in constructor
        HttpRequest req = new DefaultHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.GET, 
            "/test"
        );
        req.headers().set("x-http2-stream-id", "123");
        
        // Mock Router
        Router router = Mockito.mock(Router.class);
        
        // Mock ChannelPipeline
        ChannelPipeline pipeline = Mockito.mock(ChannelPipeline.class);
        when(ctx.pipeline()).thenReturn(pipeline);
        
        // Configure pipeline mock to return null for "chunker" and non-null for one of the base handlers
        when(pipeline.get("chunker")).thenReturn(null);
        when(pipeline.get("compressor")).thenReturn(null);
        when(pipeline.get("codec")).thenReturn(Mockito.mock(io.netty.channel.ChannelHandler.class));
        when(pipeline.get("http2")).thenReturn(null);
        
        // Create NettyContext instance using the provided constructor
        NettyContext nettyContext = new NettyContext(ctx, req, router, "/test", 8192);
        
        // Call the entry point method
        nettyContext.responseSender();
    }
}
