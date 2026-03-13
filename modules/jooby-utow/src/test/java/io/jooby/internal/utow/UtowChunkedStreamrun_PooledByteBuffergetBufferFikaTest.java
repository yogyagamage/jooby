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

class UtowChunkedStreamrun_PooledByteBuffergetBufferFikaTest {

    @Test
    void test() throws IOException {
        // Create mocks for dependencies
        ReadableByteChannel mockSource = mock(ReadableByteChannel.class);
        HttpServerExchange mockExchange = mock(HttpServerExchange.class);
        Sender mockSender = mock(Sender.class);
        IoCallback mockCallback = mock(IoCallback.class);
        ServerConnection mockConnection = mock(ServerConnection.class);
        ByteBufferPool mockByteBufferPool = mock(ByteBufferPool.class);
        PooledByteBuffer mockPooledByteBuffer = mock(PooledByteBuffer.class);
        ByteBuffer mockByteBuffer = mock(ByteBuffer.class);

        // Setup mock behavior to reach the target method
        when(mockExchange.getResponseSender()).thenReturn(mockSender);
        when(mockExchange.getConnection()).thenReturn(mockConnection);
        when(mockConnection.getByteBufferPool()).thenReturn(mockByteBufferPool);
        when(mockByteBufferPool.allocate()).thenReturn(mockPooledByteBuffer);
        when(mockPooledByteBuffer.getBuffer()).thenReturn(mockByteBuffer);
        when(mockSource.read(mockByteBuffer)).thenReturn(1);

        // Instantiate the class under test
        UtowChunkedStream instance = new UtowChunkedStream(-1);

        // Set up the instance using the send method
        instance.send(mockSource, mockExchange, mockCallback);

        // Invoke the entry point method
        instance.run();
    }
}
