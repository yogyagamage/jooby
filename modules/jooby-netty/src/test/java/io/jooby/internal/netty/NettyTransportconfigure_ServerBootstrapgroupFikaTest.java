package io.jooby.internal.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NettyTransportconfigure_ServerBootstrapgroupFikaTest {

    @Test
    void test() {
        EventLoopGroup acceptor = Mockito.mock(EventLoopGroup.class);
        EventLoopGroup eventloop = Mockito.mock(EventLoopGroup.class);
        
        NettyTransport transport = NettyTransport.transport(getClass().getClassLoader());
        transport.configure(acceptor, eventloop);
    }
}
