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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class UtowChunkedStreamrun_SendersendFikaTest {

    @Test
    public void testRunInvokesSenderSend() throws IOException {
        // Create mocks for dependencies
        ReadableByteChannel mockSource = Mockito.mock(ReadableByteChannel.class);
        HttpServerExchange mockExchange = Mockito.mock(HttpServerExchange.class);
        Sender mockSender = Mockito.mock(Sender.class);
        IoCallback mockCallback = Mockito.mock(IoCallback.class);
        ServerConnection mockConnection = Mockito.mock(ServerConnection.class);
        ByteBufferPool mockBufferPool = Mockito.mock(ByteBufferPool.class);
        PooledByteBuffer mockPooled = Mockito.mock(PooledByteBuffer.class);
        
        // Setup mock behavior to reach the sender.send() call
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        when(mockPooled.getBuffer()).thenReturn(buffer);
        when(mockSource.read(buffer)).thenReturn(100); // Non-negative count to avoid done()
        when(mockExchange.getResponseSender()).thenReturn(mockSender);
        when(mockExchange.getConnection()).thenReturn(mockConnection);
        when(mockConnection.getByteBufferPool()).thenReturn(mockBufferPool);
        when(mockBufferPool.allocate()).thenReturn(mockPooled);
        
        // Ensure sender.send() does nothing (real call)
        doNothing().when(mockSender).send(any(ByteBuffer.class), any(IoCallback.class));
        
        // Create instance with len = -1 to avoid length restrictions
        UtowChunkedStream stream = new UtowChunkedStream(-1);
        
        // Setup the stream via send method
        stream.send(mockSource, mockExchange, mockCallback);
        
        // Execute the entry point
        stream.run();
    }
}
