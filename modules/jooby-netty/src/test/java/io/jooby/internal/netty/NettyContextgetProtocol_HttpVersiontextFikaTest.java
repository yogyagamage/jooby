package io.jooby.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyContextgetProtocol_HttpVersiontextFikaTest {

    @Test
    public void testGetProtocolInvokesHttpVersionText() {
        // Mock dependencies for NettyContext constructor
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        HttpRequest req = Mockito.mock(HttpRequest.class);
        Router router = Mockito.mock(Router.class);
        String path = "/test";
        int bufferSize = 8192;

        // Mock pipeline to ensure ctx.pipeline().get("http2") returns null
        io.netty.channel.ChannelPipeline pipeline = Mockito.mock(io.netty.channel.ChannelPipeline.class);
        when(ctx.pipeline()).thenReturn(pipeline);
        when(pipeline.get("http2")).thenReturn(null);

        // Mock protocolVersion() to return a real HttpVersion instance
        HttpVersion httpVersion = HttpVersion.HTTP_1_1;
        when(req.protocolVersion()).thenReturn(httpVersion);

        // Mock headers to avoid NPE in constructor
        io.netty.handler.codec.http.HttpHeaders headers = Mockito.mock(io.netty.handler.codec.http.HttpHeaders.class);
        when(req.headers()).thenReturn(headers);
        when(headers.getAll("x-http2-stream-id")).thenReturn(null);

        // Mock method() to avoid NPE in constructor
        io.netty.handler.codec.http.HttpMethod httpMethod = Mockito.mock(io.netty.handler.codec.http.HttpMethod.class);
        when(req.method()).thenReturn(httpMethod);
        when(httpMethod.name()).thenReturn("GET");

        // Create NettyContext instance
        NettyContext nettyContext = new NettyContext(ctx, req, router, path, bufferSize);

        // Call entry point method - this should invoke HttpVersion.text()
        nettyContext.getProtocol();
    }
}
