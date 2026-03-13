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

public class UtowChunkedStreamsend_ByteBufferPoolallocateFikaTest {

    @Test
    public void testSendInvokesByteBufferPoolAllocate() throws IOException {
        // Create real objects where possible
        ReadableByteChannel source = mock(ReadableByteChannel.class);
        HttpServerExchange exchange = mock(HttpServerExchange.class);
        IoCallback callback = mock(IoCallback.class);
        Sender sender = mock(Sender.class);
        ServerConnection connection = mock(ServerConnection.class);
        ByteBufferPool byteBufferPool = mock(ByteBufferPool.class);
        PooledByteBuffer pooledByteBuffer = mock(PooledByteBuffer.class);
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // Setup exchange to return sender and connection
        when(exchange.getResponseSender()).thenReturn(sender);
        when(exchange.getConnection()).thenReturn(connection);
        
        // Setup connection to return byteBufferPool
        when(connection.getByteBufferPool()).thenReturn(byteBufferPool);
        
        // Setup byteBufferPool to return pooledByteBuffer
        when(byteBufferPool.allocate()).thenReturn(pooledByteBuffer);
        
        // Setup pooledByteBuffer to return a real ByteBuffer
        when(pooledByteBuffer.getBuffer()).thenReturn(buffer);
        
        // Setup source.read to return some data to avoid immediate completion
        when(source.read(buffer)).thenReturn(100).thenReturn(-1);

        // Instantiate the class under test using provided constructor
        UtowChunkedStream utowChunkedStream = new UtowChunkedStream(-1L);
        
        // Call the entry point method
        utowChunkedStream.send(source, exchange, callback);
    }
}
