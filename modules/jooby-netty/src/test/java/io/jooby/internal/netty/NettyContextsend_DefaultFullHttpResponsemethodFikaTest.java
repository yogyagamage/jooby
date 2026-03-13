package io.jooby.internal.netty;

import io.jooby.Router;
import io.jooby.StatusCode;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;

class NettyContextsend_DefaultFullHttpResponsemethodFikaTest {

    @Test
    void testSendStatusCode() {
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        HttpRequest req = Mockito.mock(HttpRequest.class);
        Router router = Mockito.mock(Router.class);
        String path = "/test";
        int bufferSize = 8192;

        HttpHeaders requestHeaders = new DefaultHttpHeaders();
        Mockito.when(req.headers()).thenReturn(requestHeaders);
        Mockito.when(req.method()).thenReturn(io.netty.handler.codec.http.HttpMethod.GET);

        NettyContext nettyContext = new NettyContext(ctx, req, router, path, bufferSize);
        
        nettyContext.setHeaders = new DefaultHttpHeaders(true);
        nettyContext.setHeaders.set(CONTENT_LENGTH, "0");
        
        StatusCode statusCode = StatusCode.OK;
        nettyContext.send(statusCode);
    }
}
