package io.jooby.internal.netty;

import io.jooby.Http2Configurer;
import io.jooby.Router;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.ssl.SslContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.ScheduledExecutorService;

public class NettyPipelinehttp11_HttpServerExpectContinueHandlermethodFikaTest {

    @Test
    public void testInitChannelCallsHttp11AndCreatesHttpServerExpectContinueHandler() {
        ScheduledExecutorService service = Mockito.mock(ScheduledExecutorService.class);
        Router router = Mockito.mock(Router.class);
        HttpDataFactory factory = Mockito.mock(HttpDataFactory.class);
        SslContext sslContext = null;
        Http2Configurer<Http2Extension, ChannelInboundHandler> http2 = null;
        boolean defaultHeaders = false;
        Integer compressionLevel = null;
        int bufferSize = 8192;
        long maxRequestSize = 1048576L;
        boolean is100ContinueExpected = true;

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
        Mockito.when(socketChannel.pipeline()).thenReturn(channelPipeline);
        Mockito.when(socketChannel.alloc()).thenReturn(Mockito.mock(io.netty.buffer.ByteBufAllocator.class));

        pipeline.initChannel(socketChannel);
    }
}
