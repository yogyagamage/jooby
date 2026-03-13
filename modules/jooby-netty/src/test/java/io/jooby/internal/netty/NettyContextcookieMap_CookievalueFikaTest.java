package io.jooby.internal.netty;

import io.jooby.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NettyContextcookieMap_CookievalueFikaTest {

    @Test
    public void cookieMap() {
        // Mock dependencies for constructor
        ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
        HttpRequest req = mock(HttpRequest.class);
        Router router = mock(Router.class);
        String path = "/test";
        int bufferSize = 8192;

        // Create headers with a cookie string that will trigger Cookie.value() call
        DefaultHttpHeaders headers = new DefaultHttpHeaders();
        headers.set(HttpHeaderNames.COOKIE, "sessionId=abc123; userId=testUser");
        when(req.headers()).thenReturn(headers);
        when(req.method()).thenReturn(io.netty.handler.codec.http.HttpMethod.GET);

        // Instantiate the class under test
        NettyContext nettyContext = new NettyContext(ctx, req, router, path, bufferSize);

        // Call the entry point method - this should traverse the path and invoke Cookie.value()
        nettyContext.cookieMap();
    }
}
