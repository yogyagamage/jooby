package io.jooby.internal.netty;

import io.jooby.Http2Configurer;
import io.jooby.Router;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;
import io.netty.handler.codec.http.HttpServerUpgradeHandler;
import io.netty.handler.codec.http.HttpServerUpgradeHandler.UpgradeCodec;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.ScheduledExecutorService;

class NettyPipelineinitChannel_SocketChannelallocFikaTest {

    @Test
    void testInitChannelCallsSocketChannelAlloc() {
        ScheduledExecutorService service = Mockito.mock(ScheduledExecutorService.class);
        Router router = Mockito.mock(Router.class);
        HttpDataFactory factory = Mockito.mock(HttpDataFactory.class);
        SslContext sslContext = Mockito.mock(SslContext.class);
        Http2Configurer<Http2Extension, ChannelInboundHandler> http2 = Mockito.mock(Http2Configurer.class);
        boolean defaultHeaders = false;
        Integer compressionLevel = null;
        int bufferSize = 8192;
        long maxRequestSize = 1024 * 1024;
        boolean is100ContinueExpected = false;

        NettyPipeline pipeline = new NettyPipeline(
            service,
            router,
            factory,
            sslContext,
            http2,
            defaultHeaders,
            compressionLevel,
            bufferSize,
            maxRequestSize,
            is100ContinueExpected
        );

        SocketChannel socketChannel = Mockito.mock(SocketChannel.class);
        ChannelPipeline channelPipeline = Mockito.mock(ChannelPipeline.class);
        ByteBufAllocator allocator = Mockito.mock(ByteBufAllocator.class);
        SslHandler sslHandler = Mockito.mock(SslHandler.class);

        Mockito.when(socketChannel.pipeline()).thenReturn(channelPipeline);
        Mockito.when(socketChannel.alloc()).thenReturn(allocator);
        Mockito.when(sslContext.newHandler(allocator)).thenReturn(sslHandler);

        pipeline.initChannel(socketChannel);
    }
}
