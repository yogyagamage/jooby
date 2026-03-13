package io.jooby.internal.netty;

import io.jooby.Router;
import io.jooby.Multipart;
import io.jooby.Formdata;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.multipart.HttpData;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.util.CharsetUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.charset.Charset;
import java.util.Iterator;

import static org.mockito.Mockito.when;

public class NettyContextdecodeForm_HttpDatagetStringFikaTest {

    @Test
    public void testMultipartTriggersGetString() throws Exception {
        // Create mock ChannelHandlerContext
        ChannelHandlerContext mockCtx = Mockito.mock(ChannelHandlerContext.class);
        
        // Create a real HttpRequest
        HttpRequest req = new DefaultFullHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.POST, 
            "/test"
        );
        
        // Create mock Router
        Router mockRouter = Mockito.mock(Router.class);
        
        // Create NettyContext instance using the provided constructor
        NettyContext context = new NettyContext(
            mockCtx, 
            req, 
            mockRouter, 
            "/test", 
            8192
        );
        
        // Create a mock HttpPostRequestDecoder that will return HttpData
        HttpPostRequestDecoder mockDecoder = Mockito.mock(HttpPostRequestDecoder.class);
        
        // Create mock HttpData that will be returned by the decoder
        HttpData mockHttpData = Mockito.mock(HttpData.class);
        
        // Create mock Iterator for the decoder
        Iterator<InterfaceHttpData> mockIterator = Mockito.mock(Iterator.class);
        
        // Set up the decoder to return our mock iterator and HttpData
        when(mockDecoder.hasNext()).thenReturn(true, false); // Has one item, then done
        when(mockDecoder.next()).thenReturn(mockHttpData);
        
        // Set up the iterator to return our mock HttpData
        when(mockIterator.hasNext()).thenReturn(true, false);
        when(mockIterator.next()).thenReturn(mockHttpData);
        
        // Configure the HttpData to return Attribute type (not FileUpload)
        when(mockHttpData.getHttpDataType()).thenReturn(InterfaceHttpData.HttpDataType.Attribute);
        when(mockHttpData.getName()).thenReturn("testField");
        
        // Configure getString to return a value when called with UTF_8 charset
        when(mockHttpData.getString(Mockito.any(Charset.class))).thenReturn("testValue");
        
        // Use reflection to set the decoder field on the NettyContext instance
        // This is necessary because decoder is a private field
        java.lang.reflect.Field decoderField = NettyContext.class.getDeclaredField("decoder");
        decoderField.setAccessible(true);
        decoderField.set(context, mockDecoder);
        
        // Call the entry point method - this should trigger the chain
        Multipart result = context.multipart();
    }
}
