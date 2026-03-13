package io.jooby.internal.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

class EpollTransportconfigure_ServerBootstrapchannelFikaTest {

    @Test
    void test() throws Exception {
        Constructor<?> constructor = Class.forName("io.jooby.internal.netty.NettyTransport$EpollTransport")
                .getDeclaredConstructor();
        constructor.setAccessible(true);
        Object transport = constructor.newInstance();
        
        EventLoopGroup acceptor = new EpollEventLoopGroup(1);
        EventLoopGroup eventloop = new EpollEventLoopGroup(1);
        
        try {
            Class<?> transportClass = Class.forName("io.jooby.internal.netty.NettyTransport$EpollTransport");
            var method = transportClass.getDeclaredMethod("configure", EventLoopGroup.class, EventLoopGroup.class);
            method.setAccessible(true);
            method.invoke(transport, acceptor, eventloop);
        } finally {
            acceptor.shutdownGracefully().sync();
            eventloop.shutdownGracefully().sync();
        }
    }
}
