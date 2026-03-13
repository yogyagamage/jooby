package io.jooby.internal.utow;

import io.undertow.connector.ByteBufferPool;
import io.undertow.connector.PooledByteBuffer;
import io.undertow.io.IoCallback;
import io.undertow.io.Sender;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.ServerConnection;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

import static org.mockito.Mockito.*;

class UtowChunkedStreamonComplete_HttpServerExchangeisInIoThreadFikaTest {

    @Test
    void test() throws IOException {
        // Create mocks for dependencies
        ByteBufferPool byteBufferPool = mock(ByteBufferPool.class);
        PooledByteBuffer pooledByteBuffer = mock(PooledByteBuffer.class);
        ServerConnection serverConnection = mock(ServerConnection.class);
        HttpServerExchange exchange = mock(HttpServerExchange.class);
        Sender sender = mock(Sender.class);
        ReadableByteChannel source = mock(ReadableByteChannel.class);
        IoCallback ioCallback = mock(IoCallback.class);
        
        // Configure mocks to allow execution to reach target method
        when(exchange.getResponseSender()).thenReturn(sender);
        when(exchange.getConnection()).thenReturn(serverConnection);
        when(serverConnection.getByteBufferPool()).thenReturn(byteBufferPool);
        when(byteBufferPool.allocate()).thenReturn(pooledByteBuffer);
        when(pooledByteBuffer.getBuffer()).thenReturn(ByteBuffer.allocate(1024));
        when(source.read(any(ByteBuffer.class))).thenReturn(-1);
        when(exchange.isInIoThread()).thenReturn(true);
        
        // Create instance using constructor
        UtowChunkedStream instance = new UtowChunkedStream(-1);
        
        // Call send method which will eventually call onComplete
        instance.send(source, exchange, ioCallback);
        
        // The call chain: send -> onComplete -> exchange.isInIoThread()
        // No assertions needed
    }
}
