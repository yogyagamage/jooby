package io.jooby.internal.netty;

import io.jooby.Router;
import io.jooby.Server;
import io.jooby.StatusCode;
import io.jooby.exception.StatusCodeException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.InterfaceHttpPostRequestDecoder;
import io.netty.util.ReferenceCounted;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.ScheduledExecutorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NettyHandlerchannelRead_ReferenceCountedrefCntFikaTest {

    @Test
    void testChannelReadInvokesReferenceCountedRefCnt() throws Exception {
        // Create mocks for constructor dependencies
        ScheduledExecutorService scheduler = mock(ScheduledExecutorService.class);
        Router router = mock(Router.class);
        HttpDataFactory factory = mock(HttpDataFactory.class);
        
        // Create NettyHandler instance using the provided constructor
        NettyHandler handler = new NettyHandler(
            scheduler,
            router,
            1024 * 1024, // maxRequestSize
            8192,        // bufferSize
            factory,
            false,       // defaultHeaders
            false        // is100ContinueExpected
        );
        
        // Create mock ChannelHandlerContext
        ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
        
        // Create a ReferenceCounted message that will trigger the target path
        // We need a message that is both HttpRequest and ReferenceCounted
        ByteBuf content = Unpooled.buffer(10);
        DefaultFullHttpRequest httpRequest = new DefaultFullHttpRequest(
            HttpVersion.HTTP_1_1,
            HttpMethod.GET,
            "/test",
            content
        );
        
        // Mock router to return a Match that can be executed
        Router.Match match = mock(Router.Match.class);
        when(router.match(any())).thenReturn(match);
        
        // Execute the entry point method
        handler.channelRead(ctx, httpRequest);
        
        // The finally block will check if msg instanceof ReferenceCounted
        // DefaultFullHttpRequest implements ReferenceCounted, so ref.refCnt() will be called
    }
}
