package io.jooby.internal.netty;

import io.jooby.StatusCode;
import io.jooby.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class NettyContextsend_DefaultHttpHeaderssetFikaTest2 {

    @Test
    public void testSendStatusCodeTriggersDefaultHttpHeadersSet() {
        ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
        HttpRequest req = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/");
        Router router = mock(Router.class);
        String path = "/test";
        int bufferSize = 8192;

        NettyContext nettyContext = new NettyContext(ctx, req, router, path, bufferSize);
        
        nettyContext.send(StatusCode.OK);
    }
}
