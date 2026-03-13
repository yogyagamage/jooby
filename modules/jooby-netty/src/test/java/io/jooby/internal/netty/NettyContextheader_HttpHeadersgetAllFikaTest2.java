package io.jooby.internal.netty;

import io.jooby.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NettyContextheader_HttpHeadersgetAllFikaTest2 {

    @Test
    void testHeaderMethodInvokesHttpHeadersGetAll() {
        // Create mock dependencies for NettyContext constructor
        ChannelHandlerContext mockCtx = mock(ChannelHandlerContext.class);
        Router mockRouter = mock(Router.class);
        
        // Create real HttpRequest with headers that will trigger getAll call
        HttpRequest req = new DefaultFullHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.GET, 
            "/test"
        );
        
        // Add headers to ensure names() returns at least one entry
        HttpHeaders headers = req.headers();
        headers.add("Test-Header", "value1");
        headers.add("Test-Header", "value2");
        headers.add("Another-Header", "single-value");
        
        // Create NettyContext instance using the constructor
        NettyContext context = new NettyContext(
            mockCtx,
            req,
            mockRouter,
            "/test",
            8192
        );
        
        // Call the entry point method - this should traverse the path
        // and invoke HttpHeaders.getAll() internally
        context.header();
    }
}
