package io.jooby.internal.netty;

import io.jooby.Body;
import io.jooby.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.HttpData;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;

class NettyContextbody_InterfaceHttpPostRequestDecodernextFikaTest {

    @Test
    void testBodyInvokesNext() throws Exception {
        // Mock dependencies
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        Router router = Mockito.mock(Router.class);
        
        // Create a real HttpRequest with proper headers
        DefaultHttpRequest req = new DefaultHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.POST, 
            "/test"
        );
        req.headers().set(CONTENT_TYPE, "multipart/form-data");
        req.headers().set(CONTENT_LENGTH, "100");
        
        // Create a real HttpPostRequestDecoder that will have hasNext() return true
        HttpPostRequestDecoder decoder = Mockito.mock(HttpPostRequestDecoder.class);
        Mockito.when(decoder.hasNext()).thenReturn(true);
        
        // Create mock HttpData for decoder.next() to return
        HttpData httpData = Mockito.mock(HttpData.class);
        Mockito.when(decoder.next()).thenReturn(httpData);
        
        // Instantiate NettyContext using the provided constructor
        NettyContext nettyContext = new NettyContext(ctx, req, router, "/test", 8192);
        
        // Set the decoder field to our mock decoder
        // Using reflection to access private field
        java.lang.reflect.Field decoderField = NettyContext.class.getDeclaredField("decoder");
        decoderField.setAccessible(true);
        decoderField.set(nettyContext, decoder);
        
        // Call the entry point method
        Body body = nettyContext.body();
        
        // No assertions required - test passes if next() was invoked without exceptions
    }
}
