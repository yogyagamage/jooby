package io.jooby.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.ssl.ApplicationProtocolNames;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.Consumer;

public class Http2OrHttp11HandlerconfigurePipeline_ChannelHandlerContextpipelineFikaTest {

    @Test
    public void test() {
        Consumer<ChannelPipeline> http1Consumer = Mockito.mock(Consumer.class);
        Consumer<ChannelPipeline> http2Consumer = Mockito.mock(Consumer.class);
        
        Http2OrHttp11Handler handler = new Http2OrHttp11Handler(http1Consumer, http2Consumer);
        
        ChannelHandlerContext ctx = Mockito.mock(ChannelHandlerContext.class);
        ChannelPipeline pipeline = Mockito.mock(ChannelPipeline.class);
        
        Mockito.when(ctx.pipeline()).thenReturn(pipeline);
        
        handler.configurePipeline(ctx, ApplicationProtocolNames.HTTP_1_1);
    }
}
