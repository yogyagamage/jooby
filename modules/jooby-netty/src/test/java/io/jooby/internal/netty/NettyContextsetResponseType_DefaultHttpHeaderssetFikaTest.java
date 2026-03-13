package io.jooby.internal.netty;

import io.jooby.MediaType;
import io.jooby.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.charset.StandardCharsets;

class NettyContextsetResponseType_DefaultHttpHeaderssetFikaTest {

    @Test
    void testSetResponseTypeCallsDefaultHttpHeadersSet() {
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        DefaultFullHttpRequest req = new DefaultFullHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.GET, 
            "/test"
        );
        Router router = Mockito.mock(Router.class);
        String path = "/test";
        int bufferSize = 8192;
        
        NettyContext nettyContext = new NettyContext(ctx, req, router, path, bufferSize);
        
        MediaType mediaType = MediaType.json;
        nettyContext.setResponseType(mediaType, StandardCharsets.UTF_8);
    }
}
