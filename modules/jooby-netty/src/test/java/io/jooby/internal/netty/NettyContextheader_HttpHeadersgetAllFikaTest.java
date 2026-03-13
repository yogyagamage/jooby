package io.jooby.internal.netty;

import io.jooby.Router;
import io.jooby.Value;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyContextheader_HttpHeadersgetAllFikaTest {

    @Test
    public void testHeaderCallsHttpHeadersGetAll() {
        // Mock dependencies for NettyContext constructor
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        HttpRequest req = Mockito.mock(HttpRequest.class);
        Router router = Mockito.mock(Router.class);
        String path = "/test";
        int bufferSize = 8192;

        // Create real HttpHeaders that will be returned by req.headers()
        HttpHeaders headers = new DefaultHttpHeaders();
        headers.add("Test-Header", "value1");
        headers.add("Test-Header", "value2");
        
        // Configure mock to return the real headers
        when(req.headers()).thenReturn(headers);
        when(req.method()).thenReturn(io.netty.handler.codec.http.HttpMethod.GET);

        // Create instance using constructor
        NettyContext nettyContext = new NettyContext(ctx, req, router, path, bufferSize);
        
        // Call entry point method - this should trigger HttpHeaders.getAll()
        Value result = nettyContext.header("Test-Header");
    }
}
