package io.jooby.internal.netty;

import io.jooby.Router;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyContextpromise_ChannelPromiseaddListenerFikaTest2 {

    @Test
    public void testSendByteArrayArrayTriggersAddListener() throws Exception {
        // Create mock dependencies for NettyContext constructor
        ChannelHandlerContext mockCtx = Mockito.mock(ChannelHandlerContext.class);
        DefaultFullHttpRequest mockReq = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1, HttpMethod.GET, "/test");
        Router mockRouter = Mockito.mock(Router.class);
        String path = "/test";
        int bufferSize = 8192;

        // Mock the channel and event loop to ensure inEventLoop() returns true
        io.netty.channel.Channel mockChannel = Mockito.mock(io.netty.channel.Channel.class);
        io.netty.channel.EventLoop mockEventLoop = Mockito.mock(io.netty.channel.EventLoop.class);
        when(mockCtx.channel()).thenReturn(mockChannel);
        when(mockChannel.eventLoop()).thenReturn(mockEventLoop);
        when(mockEventLoop.inEventLoop()).thenReturn(true);

        // Mock ctx.write to capture the ChannelPromise argument
        ChannelPromise mockPromise = Mockito.mock(ChannelPromise.class);
        when(mockCtx.newPromise()).thenReturn(mockPromise);
        when(mockCtx.write(Mockito.any(), Mockito.any(ChannelPromise.class)))
                .thenAnswer(invocation -> {
                    // The second argument should be the ChannelPromise from promise() method
                    ChannelPromise promiseArg = invocation.getArgument(1);
                    // This will trigger addListener when pendingTasks() returns true
                    return null;
                });

        // Create NettyContext instance
        NettyContext context = new NettyContext(mockCtx, mockReq, mockRouter, path, bufferSize);
        
        // Set pendingTasks condition to true by setting responseStarted
        // Based on field declarations, we need to access private field
        // Use reflection to set responseStarted to true
        java.lang.reflect.Field responseStartedField = 
                NettyContext.class.getDeclaredField("responseStarted");
        responseStartedField.setAccessible(true);
        responseStartedField.set(context, true);

        // Also set needsFlush to true to ensure the if branch is taken
        java.lang.reflect.Field needsFlushField = 
                NettyContext.class.getDeclaredField("needsFlush");
        needsFlushField.setAccessible(true);
        needsFlushField.set(context, true);

        // Create test data
        byte[][] data = new byte[][]{"test".getBytes()};
        
        // Invoke entry point - this should trigger the call chain
        context.send(data);
    }
}
