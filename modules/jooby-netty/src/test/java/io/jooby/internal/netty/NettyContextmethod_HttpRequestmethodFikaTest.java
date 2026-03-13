package io.jooby.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyContextmethod_HttpRequestmethodFikaTest {

    @Test
    public void testMethodCallChain() {
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        HttpRequest req = Mockito.mock(HttpRequest.class);
        Router router = Mockito.mock(Router.class);
        String path = "/test";
        int bufferSize = 8192;

        io.netty.handler.codec.http.HttpMethod httpMethod = 
            io.netty.handler.codec.http.HttpMethod.GET;
        when(req.method()).thenReturn(httpMethod);
        
        io.netty.handler.codec.http.HttpHeaders headers = 
            Mockito.mock(io.netty.handler.codec.http.HttpHeaders.class);
        when(req.headers()).thenReturn(headers);

        NettyContext nettyContext = new NettyContext(ctx, req, router, path, bufferSize);
    }
}
