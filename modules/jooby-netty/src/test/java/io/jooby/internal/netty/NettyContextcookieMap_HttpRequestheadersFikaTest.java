package io.jooby.internal.netty;

import io.jooby.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.DefaultHttpRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.mockito.Mockito.when;

public class NettyContextcookieMap_HttpRequestheadersFikaTest {

    @Test
    public void testCookieMapCallsHeaders() {
        // Create mock dependencies for constructor
        ChannelHandlerContext mockCtx = Mockito.mock(ChannelHandlerContext.class);
        HttpRequest mockReq = Mockito.mock(HttpRequest.class);
        Router mockRouter = Mockito.mock(Router.class);
        String path = "/test";
        int bufferSize = 8192;

        // Configure the mock request to return null for cookie header
        // This ensures the path through cookieMap() reaches req.headers().get()
        when(mockReq.headers()).thenReturn(new io.netty.handler.codec.http.DefaultHttpHeaders());
        when(mockReq.method()).thenReturn(io.netty.handler.codec.http.HttpMethod.GET);

        // Instantiate the class under test using the provided constructor
        NettyContext context = new NettyContext(mockCtx, mockReq, mockRouter, path, bufferSize);

        // Call the entry point method - this should trigger req.headers() internally
        Map<String, String> result = context.cookieMap();
    }
}
