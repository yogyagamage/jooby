package io.jooby.internal.netty;

import io.jooby.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.mockito.Mockito.when;

public class NettyContextcookieMap_HttpHeadersgetFikaTest {

    @Test
    public void testCookieMapInvokesHttpHeadersGet() {
        // Create mock HttpRequest with headers that will trigger the cookie parsing path
        HttpRequest mockReq = Mockito.mock(HttpRequest.class);
        HttpHeaders mockHeaders = new DefaultHttpHeaders();
        mockHeaders.set(HttpHeaderNames.COOKIE, "test=cookie");
        when(mockReq.headers()).thenReturn(mockHeaders);
        when(mockReq.method()).thenReturn(io.netty.handler.codec.http.HttpMethod.GET);
        
        // Create other required mocks
        ChannelHandlerContext mockCtx = Mockito.mock(ChannelHandlerContext.class);
        Router mockRouter = Mockito.mock(Router.class);
        
        // Instantiate NettyContext using the provided constructor
        NettyContext context = new NettyContext(
            mockCtx,
            mockReq,
            mockRouter,
            "/test",
            8192
        );
        
        // Call the entry point method - this should trigger HttpHeaders.get(HttpHeaderNames.COOKIE)
        Map<String, String> result = context.cookieMap();
    }
}
