package io.jooby.internal.netty;

import io.jooby.Router;
import io.jooby.ServerSentEmitter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class NettyContextupgrade_DefaultHttpResponsemethodFikaTest {

    @Test
    public void testUpgradeCallsDefaultHttpResponseConstructor() throws Exception {
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        HttpRequest req = Mockito.mock(HttpRequest.class);
        Router router = Mockito.mock(Router.class);
        String path = "/test";
        int bufferSize = 8192;
        
        DefaultHttpHeaders mockHeaders = new DefaultHttpHeaders();
        Mockito.when(req.headers()).thenReturn(mockHeaders);
        Mockito.when(req.method()).thenReturn(io.netty.handler.codec.http.HttpMethod.GET);
        
        NettyContext nettyContext = new NettyContext(ctx, req, router, path, bufferSize);
        
        ServerSentEmitter.Handler handler = Mockito.mock(ServerSentEmitter.Handler.class);
        
        nettyContext.upgrade(handler);
    }
}
