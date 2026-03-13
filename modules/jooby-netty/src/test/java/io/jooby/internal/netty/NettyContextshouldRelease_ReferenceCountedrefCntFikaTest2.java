package io.jooby.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyContextshouldRelease_ReferenceCountedrefCntFikaTest2 {

    @Test
    public void testMultipartTriggersRefCnt() throws Exception {
        // Create a real HttpRequest that implements ReferenceCounted
        HttpRequest req = new DefaultFullHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.POST, 
            "/test"
        );
        
        // Mock ChannelHandlerContext
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        
        // Mock Router
        Router router = Mockito.mock(Router.class);
        
        // Create InterfaceHttpPostRequestDecoder that is not null and not HttpRawPostRequestDecoder
        InterfaceHttpPostRequestDecoder decoder = Mockito.mock(HttpPostRequestDecoder.class);
        when(decoder.hasNext()).thenReturn(false); // Ensure while loop doesn't execute
        
        // Create NettyContext instance
        NettyContext nettyContext = new NettyContext(
            ctx, 
            req, 
            router, 
            "/test", 
            8192
        );
        
        // Set the decoder field via reflection to ensure decodeForm doesn't return early
        java.lang.reflect.Field decoderField = NettyContext.class.getDeclaredField("decoder");
        decoderField.setAccessible(true);
        decoderField.set(nettyContext, decoder);
        
        // Call the entry point method
        nettyContext.multipart();
    }
}
