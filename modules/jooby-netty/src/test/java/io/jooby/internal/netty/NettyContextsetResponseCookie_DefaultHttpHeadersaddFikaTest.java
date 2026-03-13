package io.jooby.internal.netty;

import io.jooby.Cookie;
import io.jooby.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.netty.handler.codec.http.HttpHeaderNames.SET_COOKIE;

public class NettyContextsetResponseCookie_DefaultHttpHeadersaddFikaTest {

    @Test
    public void testSetResponseCookieTriggersDefaultHttpHeadersAdd() {
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        HttpRequest req = Mockito.mock(HttpRequest.class);
        Router router = Mockito.mock(Router.class);
        String path = "/test";
        int bufferSize = 8192;

        DefaultHttpHeaders requestHeaders = new DefaultHttpHeaders();
        Mockito.when(req.headers()).thenReturn(requestHeaders);
        Mockito.when(req.method()).thenReturn(io.netty.handler.codec.http.HttpMethod.GET);

        NettyContext nettyContext = new NettyContext(ctx, req, router, path, bufferSize);
        
        Cookie cookie = new Cookie("testCookie", "testValue");
        nettyContext.setResponseCookie(cookie);
    }
}
