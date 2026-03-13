package io.jooby.internal.netty;

import io.jooby.Router;
import io.jooby.StatusCode;
import io.jooby.exception.StatusCodeException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder;
import io.netty.util.AsciiString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.ScheduledExecutorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NettyHandlerchannelRead_ByteBufreadableBytesFikaTest {

    @Test
    void testChannelReadPathToByteBufReadableBytes() throws Exception {
        // Create mocks for constructor dependencies
        ScheduledExecutorService scheduler = mock(ScheduledExecutorService.class);
        Router router = mock(Router.class);
        HttpDataFactory factory = mock(HttpDataFactory.class);
        
        // Create the handler with test configuration
        NettyHandler handler = new NettyHandler(
            scheduler,
            router,
            1024 * 1024, // maxRequestSize
            8192, // bufferSize
            factory,
            false, // defaultHeaders
            false // is100ContinueExpected
        );
        
        // Create mock ChannelHandlerContext
        ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
        
        // First, send an HTTP request to set up the decoder
        HttpRequest httpRequest = new DefaultHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.POST, 
            "/test"
        );
        httpRequest.headers().set("Content-Length", "100");
        
        handler.channelRead(ctx, httpRequest);
        
        // Now send an HttpContent chunk to trigger the ByteBuf.readableBytes() call
        HttpContent chunk = mock(HttpContent.class);
        ByteBuf byteBuf = Unpooled.buffer(50);
        when(chunk.content()).thenReturn(byteBuf);
        
        // Mock the decoder to be non-null (set by the first call)
        // We need to set up the decoder field via reflection since it's private
        java.lang.reflect.Field decoderField = NettyHandler.class.getDeclaredField("decoder");
        decoderField.setAccessible(true);
        InterfaceHttpPostRequestDecoder mockDecoder = mock(InterfaceHttpPostRequestDecoder.class);
        decoderField.set(handler, mockDecoder);
        
        // Mock the context field to avoid NPE
        java.lang.reflect.Field contextField = NettyHandler.class.getDeclaredField("context");
        contextField.setAccessible(true);
        Object context = mock(io.jooby.internal.netty.NettyContext.class);
        contextField.set(handler, context);
        
        // Mock router.match to return a valid match
        Router.Match match = mock(Router.Match.class);
        when(match.matches()).thenReturn(true);
        when(router.match(any())).thenReturn(match);
        
        // Send the chunk - this should trigger chunk.content().readableBytes()
        handler.channelRead(ctx, chunk);
        
        // Send LastHttpContent to complete the request
        LastHttpContent lastChunk = LastHttpContent.EMPTY_LAST_CONTENT;
        handler.channelRead(ctx, lastChunk);
    }
}
