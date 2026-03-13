package io.jooby.internal.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.jooby.Router;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.InetSocketAddress;
import java.net.InetAddress;

import static org.mockito.Mockito.when;

public class NettyContextgetRemoteAddress_ChannelremoteAddressFikaTest {

    @Test
    public void testGetRemoteAddressInvokesChannelRemoteAddress() throws Exception {
        // Create mock ChannelHandlerContext
        ChannelHandlerContext mockCtx = Mockito.mock(ChannelHandlerContext.class);
        
        // Create mock Channel
        Channel mockChannel = Mockito.mock(Channel.class);
        
        // Create a real InetSocketAddress for remoteAddress
        InetSocketAddress remoteSocketAddress = new InetSocketAddress(
            InetAddress.getByName("127.0.0.1"), 8080
        );
        
        // Configure the mock channel to return the InetSocketAddress
        when(mockChannel.remoteAddress()).thenReturn(remoteSocketAddress);
        
        // Configure the mock context to return the mock channel
        when(mockCtx.channel()).thenReturn(mockChannel);
        
        // Create a real HttpRequest with headers
        DefaultHttpRequest httpRequest = new DefaultHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.GET, 
            "/test"
        );
        
        // Create mock Router
        Router mockRouter = Mockito.mock(Router.class);
        
        // Create NettyContext instance using the constructor
        NettyContext nettyContext = new NettyContext(
            mockCtx,
            httpRequest,
            mockRouter,
            "/test",
            8192
        );
        
        // Call the entry point method - this should invoke Channel.remoteAddress()
        nettyContext.getRemoteAddress();
    }
}
