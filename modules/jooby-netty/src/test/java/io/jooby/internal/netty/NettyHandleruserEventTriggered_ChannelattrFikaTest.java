package io.jooby.internal.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.ScheduledExecutorService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NettyHandleruserEventTriggered_ChannelattrFikaTest {

    @Test
    public void userEventTriggered_Channelattr() throws Exception {
        ScheduledExecutorService scheduler = mock(ScheduledExecutorService.class);
        io.jooby.Router router = mock(io.jooby.Router.class);
        long maxRequestSize = 1024L;
        int bufferSize = 8192;
        io.netty.handler.codec.http.multipart.HttpDataFactory factory = 
            mock(io.netty.handler.codec.http.multipart.HttpDataFactory.class);
        boolean defaultHeaders = false;
        boolean is100ContinueExpected = false;

        NettyHandler nettyHandler = new NettyHandler(
            scheduler, router, maxRequestSize, bufferSize, factory, 
            defaultHeaders, is100ContinueExpected
        );

        ChannelHandlerContext ctx = mock(ChannelHandlerContext.class);
        Channel channel = mock(Channel.class);
        when(ctx.channel()).thenReturn(channel);
        
        AttributeKey<NettyWebSocket> key = NettyWebSocket.WS;
        Attribute<NettyWebSocket> attribute = mock(Attribute.class);
        when(channel.attr(key)).thenReturn(attribute);
        
        IdleStateEvent idleStateEvent = mock(IdleStateEvent.class);
        
        nettyHandler.userEventTriggered(ctx, idleStateEvent);
    }
}
