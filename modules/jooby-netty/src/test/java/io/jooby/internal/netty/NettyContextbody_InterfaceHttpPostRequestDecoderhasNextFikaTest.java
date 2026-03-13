package io.jooby.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyContextbody_InterfaceHttpPostRequestDecoderhasNextFikaTest {

    @Test
    public void testBodyInvokesHasNext() {
        // Mock dependencies for constructor
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        HttpRequest req = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, "/test");
        Router router = Mockito.mock(Router.class);
        String path = "/test";
        int bufferSize = 8192;

        // Create instance
        NettyContext nettyContext = new NettyContext(ctx, req, router, path, bufferSize);

        // Create and set decoder mock
        InterfaceHttpPostRequestDecoder decoder = Mockito.mock(InterfaceHttpPostRequestDecoder.class);
        nettyContext.decoder = decoder;

        // Call entry point
        nettyContext.body();
    }
}
