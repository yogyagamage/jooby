package io.jooby.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyContextquery_HttpRequesturiFikaTest {

    @Test
    public void testQueryCallsUri() {
        // Create mock HttpRequest with proper headers and uri
        HttpRequest mockReq = Mockito.mock(HttpRequest.class);
        when(mockReq.uri()).thenReturn("/test?param=value");
        when(mockReq.method()).thenReturn(HttpMethod.GET);
        when(mockReq.headers()).thenReturn(new DefaultHttpHeaders());
        when(mockReq.protocolVersion()).thenReturn(HttpVersion.HTTP_1_1);
        
        // Create mock ChannelHandlerContext
        ChannelHandlerContext mockCtx = Mockito.mock(ChannelHandlerContext.class);
        
        // Create mock Router
        Router mockRouter = Mockito.mock(Router.class);
        
        // Create NettyContext instance using constructor
        NettyContext context = new NettyContext(
            mockCtx,
            mockReq,
            mockRouter,
            "/test",
            8192
        );
        
        // Call the entry point method - this should trigger the third-party method call
        context.query();
    }
}
