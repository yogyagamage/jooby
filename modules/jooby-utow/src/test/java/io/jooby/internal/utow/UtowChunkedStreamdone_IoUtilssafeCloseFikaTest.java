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
import java.nio.channels.ReadableByteChannel;

import static org.mockito.Mockito.*;

public class UtowChunkedStreamdone_IoUtilssafeCloseFikaTest {

    @Test
    public void test() throws IOException {
        // Create instance using constructor
        UtowChunkedStream instance = new UtowChunkedStream(-1L);
        
        // Create mocks for required fields
        ReadableByteChannel source = mock(ReadableByteChannel.class);
        HttpServerExchange exchange = mock(HttpServerExchange.class);
        Sender sender = mock(Sender.class);
        IoCallback callback = mock(IoCallback.class);
        ServerConnection connection = mock(ServerConnection.class);
        ByteBufferPool byteBufferPool = mock(ByteBufferPool.class);
        PooledByteBuffer pooledByteBuffer = mock(PooledByteBuffer.class);
        
        // Setup exchange to return connection and sender
        when(exchange.getConnection()).thenReturn(connection);
        when(exchange.getResponseSender()).thenReturn(sender);
        
        // Setup connection to return byte buffer pool
        when(connection.getByteBufferPool()).thenReturn(byteBufferPool);
        
        // Setup byte buffer pool to return pooled byte buffer
        when(byteBufferPool.allocate()).thenReturn(pooledByteBuffer);
        
        // Setup pooled byte buffer to return a buffer (not strictly needed but avoids NPE)
        when(pooledByteBuffer.getBuffer()).thenReturn(java.nio.ByteBuffer.allocate(1024));
        
        // Call send method to set up instance fields
        instance.send(source, exchange, callback);
        
        // Create exception for onException call
        IOException ex = new IOException("Test exception");
        
        // Call entry point method
        instance.onException(exchange, sender, ex);
    }
}
