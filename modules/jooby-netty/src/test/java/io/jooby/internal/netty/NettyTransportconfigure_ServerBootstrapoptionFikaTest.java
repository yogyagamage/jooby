package io.jooby.internal.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.junit.jupiter.api.Test;

class NettyTransportconfigure_ServerBootstrapoptionFikaTest {

    @Test
    void test() {
        EventLoopGroup acceptor = new NioEventLoopGroup();
        EventLoopGroup eventloop = new NioEventLoopGroup();
        
        NettyTransport transport = NettyTransport.transport(getClass().getClassLoader());
        transport.configure(acceptor, eventloop);
        
        acceptor.shutdownGracefully();
        eventloop.shutdownGracefully();
    }
}
