package io.jooby.internal.netty;

import io.jooby.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class NettyContextgetResponseCode_HttpResponseStatuscodeFikaTest {

    @Test
    public void testGetResponseCode() {
        // Create mocks for constructor dependencies
        ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
        Router router = mock(Router.class);
        
        // Create a real HttpRequest with proper headers to avoid NPE
        HttpRequest req = new DefaultHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.GET, 
            "/test"
        );
        
        // Create instance using constructor
        NettyContext nettyContext = new NettyContext(
            ctx, 
            req, 
            router, 
            "/test", 
            8192
        );
        
        // Call the entry point method
        nettyContext.getResponseCode();
    }
}
