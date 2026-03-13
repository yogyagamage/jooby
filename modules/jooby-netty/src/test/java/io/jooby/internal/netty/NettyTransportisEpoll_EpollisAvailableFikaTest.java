package io.jooby.internal.netty;

import io.netty.channel.epoll.Epoll;
import org.junit.jupiter.api.Test;

class NettyTransportisEpoll_EpollisAvailableFikaTest {

    @Test
    void test() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        NettyTransport.transport(loader);
    }
}
