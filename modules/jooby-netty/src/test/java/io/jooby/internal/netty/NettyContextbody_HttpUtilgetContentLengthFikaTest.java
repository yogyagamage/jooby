package io.jooby.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.HttpData;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyContextbody_HttpUtilgetContentLengthFikaTest {

    @Test
    public void testBodyCallsHttpUtilGetContentLength() {
        // Mock dependencies for constructor
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        HttpRequest req = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/test");
        Router router = Mockito.mock(Router.class);
        String path = "/test";
        int bufferSize = 8192;

        // Create instance
        NettyContext nettyContext = new NettyContext(ctx, req, router, path, bufferSize);

        // Setup decoder to have data
        InterfaceHttpPostRequestDecoder decoder = Mockito.mock(InterfaceHttpPostRequestDecoder.class);
        HttpData httpData = Mockito.mock(HttpData.class);
        
        // Use reflection to set the private decoder field
        try {
            java.lang.reflect.Field decoderField = NettyContext.class.getDeclaredField("decoder");
            decoderField.setAccessible(true);
            decoderField.set(nettyContext, decoder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Configure decoder to return data
        when(decoder.hasNext()).thenReturn(true);
        when(decoder.next()).thenReturn(httpData);

        // Call entry point - this should trigger HttpUtil.getContentLength
        nettyContext.body();
    }
}
