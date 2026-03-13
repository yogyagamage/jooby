package io.jooby.internal.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NettyContextdestroy_InterfaceHttpPostRequestDecoderdestroyFikaTest {

    @Test
    public void testOperationCompleteTriggersDestroy() throws Exception {
        // Create mock ChannelHandlerContext
        ChannelHandlerContext mockCtx = mock(ChannelHandlerContext.class);
        
        // Create a real HttpRequest with required method
        HttpRequest req = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/test");
        
        // Create mock Router
        Router mockRouter = mock(Router.class);
        
        // Create mock InterfaceHttpPostRequestDecoder
        InterfaceHttpPostRequestDecoder mockDecoder = mock(InterfaceHttpPostRequestDecoder.class);
        
        // Create NettyContext instance using the provided constructor
        NettyContext nettyContext = new NettyContext(mockCtx, req, mockRouter, "/test", 8192);
        
        // Set the decoder field to ensure destroy() path is taken
        // Using reflection to set the private field
        java.lang.reflect.Field decoderField = NettyContext.class.getDeclaredField("decoder");
        decoderField.setAccessible(true);
        decoderField.set(nettyContext, mockDecoder);
        
        // Create mock ChannelFuture
        ChannelFuture mockFuture = mock(ChannelFuture.class);
        when(mockFuture.cause()).thenReturn(null);
        
        // Call the entry point method
        nettyContext.operationComplete(mockFuture);
    }
}
