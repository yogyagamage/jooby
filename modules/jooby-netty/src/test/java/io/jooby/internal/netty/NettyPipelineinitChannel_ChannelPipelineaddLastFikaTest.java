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

import java.util.concurrent.ScheduledExecutorService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NettyPipelineinitChannel_ChannelPipelineaddLastFikaTest {

    @Test
    public void test() throws Exception {
        ScheduledExecutorService service = mock(ScheduledExecutorService.class);
        Router router = mock(Router.class);
        HttpDataFactory factory = mock(HttpDataFactory.class);
        SslContext sslContext = mock(SslContext.class);
        Http2Configurer<Http2Extension, ChannelInboundHandler> http2 = null;
        boolean defaultHeaders = false;
        Integer compressionLevel = null;
        int bufferSize = 8192;
        long maxRequestSize = 8192L;
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

        SocketChannel socketChannel = mock(SocketChannel.class);
        ChannelPipeline channelPipeline = mock(ChannelPipeline.class);
        ByteBufAllocator allocator = mock(ByteBufAllocator.class);
        SslHandler sslHandler = mock(SslHandler.class);

        when(socketChannel.pipeline()).thenReturn(channelPipeline);
        when(socketChannel.alloc()).thenReturn(allocator);
        when(sslContext.newHandler(allocator)).thenReturn(sslHandler);

        pipeline.initChannel(socketChannel);
    }
}
