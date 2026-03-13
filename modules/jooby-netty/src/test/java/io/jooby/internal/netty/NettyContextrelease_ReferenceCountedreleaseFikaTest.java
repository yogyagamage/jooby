package io.jooby.internal.netty;

import io.jooby.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.util.ReferenceCounted;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyContextrelease_ReferenceCountedreleaseFikaTest {

    @Test
    public void testMultipartTriggersRelease() throws Exception {
        // Create mock dependencies for NettyContext constructor
        ChannelHandlerContext mockCtx = Mockito.mock(ChannelHandlerContext.class);
        Router mockRouter = Mockito.mock(Router.class);
        
        // Create a real HttpRequest that implements ReferenceCounted
        HttpRequest req = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, "/test");
        
        // Create the NettyContext instance using the provided constructor
        NettyContext context = new NettyContext(mockCtx, req, mockRouter, "/test", 8192);
        
        // Set up the decoder to ensure decodeForm doesn't return early
        // We need a decoder that is not null and not HttpRawPostRequestDecoder
        HttpPostRequestDecoder mockDecoder = Mockito.mock(HttpPostRequestDecoder.class);
        when(mockDecoder.hasNext()).thenReturn(false); // No form data, just trigger finally block
        
        // Use reflection to set the private decoder field
        java.lang.reflect.Field decoderField = NettyContext.class.getDeclaredField("decoder");
        decoderField.setAccessible(true);
        decoderField.set(context, mockDecoder);
        
        // Call the entry point method - this should trigger the call chain
        context.multipart();
        
        // The test will pass if the execution reaches ReferenceCounted.release()
        // No assertions needed as per requirements
    }
}
