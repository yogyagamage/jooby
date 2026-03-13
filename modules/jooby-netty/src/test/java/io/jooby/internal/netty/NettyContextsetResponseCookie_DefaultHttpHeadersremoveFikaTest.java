package io.jooby.internal.netty;

import io.jooby.Cookie;
import io.jooby.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.netty.handler.codec.http.HttpHeaderNames.SET_COOKIE;

public class NettyContextsetResponseCookie_DefaultHttpHeadersremoveFikaTest {

    @Test
    public void testSetResponseCookieTriggersRemove() {
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        HttpRequest req = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/");
        Router router = Mockito.mock(Router.class);
        String path = "/";
        int bufferSize = 8192;
        
        NettyContext nettyContext = new NettyContext(ctx, req, router, path, bufferSize);
        
        Cookie cookie = new Cookie("testCookie", "testValue");
        nettyContext.setResponseCookie(cookie);
    }
}
