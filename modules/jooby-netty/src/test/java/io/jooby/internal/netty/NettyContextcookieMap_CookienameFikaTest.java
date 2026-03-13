package io.jooby.internal.netty;

import io.jooby.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NettyContextcookieMap_CookienameFikaTest {

    @Test
    void cookieMap() {
        ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
        DefaultHttpRequest req = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/");
        req.headers().set("Cookie", "sessionId=abc123; userId=456");
        Router router = mock(Router.class);
        String path = "/";
        int bufferSize = 8192;

        NettyContext nettyContext = new NettyContext(ctx, req, router, path, bufferSize);
        nettyContext.cookieMap();
    }
}
