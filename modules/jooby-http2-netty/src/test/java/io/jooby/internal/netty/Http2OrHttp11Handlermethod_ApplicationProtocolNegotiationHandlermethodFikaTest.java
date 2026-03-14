package io.jooby.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.ssl.ApplicationProtocolNames;
import io.netty.handler.ssl.ApplicationProtocolNegotiationHandler;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

class Http2OrHttp11Handlermethod_ApplicationProtocolNegotiationHandlermethodFikaTest {

    @Test
    void test() {
        Consumer<ChannelPipeline> http1 = pipeline -> {};
        Consumer<ChannelPipeline> http2 = pipeline -> {};
        
        new Http2OrHttp11Handler(http1, http2);
    }
}
