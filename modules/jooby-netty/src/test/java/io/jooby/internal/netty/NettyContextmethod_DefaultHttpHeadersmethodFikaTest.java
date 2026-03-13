package io.jooby.internal.netty;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.jooby.Router;

class NettyContextmethod_DefaultHttpHeadersmethodFikaTest {

    @Test
    void testConstructorTriggersDefaultHttpHeadersInit() {
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        HttpRequest req = Mockito.mock(HttpRequest.class);
        Router router = Mockito.mock(Router.class);
        
        HttpMethod httpMethod = Mockito.mock(HttpMethod.class);
        Mockito.when(httpMethod.name()).thenReturn("GET");
        Mockito.when(req.method()).thenReturn(httpMethod);
        
        HttpHeaders headers = Mockito.mock(HttpHeaders.class);
        Mockito.when(req.headers()).thenReturn(headers);
        
        String path = "/test";
        int bufferSize = 8192;
        
        new NettyContext(ctx, req, router, path, bufferSize);
    }
}
