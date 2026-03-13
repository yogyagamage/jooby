package io.jooby.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyContextgetProtocol_HttpRequestprotocolVersionFikaTest {

    @Test
    public void testGetProtocolCallsProtocolVersion() {
        // Mock dependencies for NettyContext constructor
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        HttpRequest req = Mockito.mock(HttpRequest.class);
        Router router = Mockito.mock(Router.class);
        String path = "/test";
        int bufferSize = 8192;

        // Setup mock behavior to avoid NPE in constructor
        when(req.headers()).thenReturn(new io.netty.handler.codec.http.DefaultHttpHeaders());
        when(req.method()).thenReturn(io.netty.handler.codec.http.HttpMethod.GET);
        when(ctx.pipeline()).thenReturn(Mockito.mock(io.netty.channel.ChannelPipeline.class));

        // Setup mock behavior for getProtocol() execution path
        when(req.protocolVersion()).thenReturn(HttpVersion.HTTP_1_1);

        // Create instance
        NettyContext nettyContext = new NettyContext(ctx, req, router, path, bufferSize);

        // Call entry point method
        nettyContext.getProtocol();
    }
}
