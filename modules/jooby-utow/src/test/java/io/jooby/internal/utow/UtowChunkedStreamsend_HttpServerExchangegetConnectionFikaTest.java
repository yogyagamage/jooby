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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UtowChunkedStreamsend_HttpServerExchangegetConnectionFikaTest {

    @Test
    public void test() throws IOException {
        // Create mocks for dependencies
        ReadableByteChannel mockSource = mock(ReadableByteChannel.class);
        HttpServerExchange mockExchange = mock(HttpServerExchange.class);
        IoCallback mockCallback = mock(IoCallback.class);
        Sender mockSender = mock(Sender.class);
        ServerConnection mockConnection = mock(ServerConnection.class);
        ByteBufferPool mockByteBufferPool = mock(ByteBufferPool.class);
        PooledByteBuffer mockPooledByteBuffer = mock(PooledByteBuffer.class);
        ByteBuffer mockByteBuffer = mock(ByteBuffer.class);

        // Setup mock behavior
        when(mockExchange.getResponseSender()).thenReturn(mockSender);
        when(mockExchange.getConnection()).thenReturn(mockConnection);
        when(mockConnection.getByteBufferPool()).thenReturn(mockByteBufferPool);
        when(mockByteBufferPool.allocate()).thenReturn(mockPooledByteBuffer);
        when(mockPooledByteBuffer.getBuffer()).thenReturn(mockByteBuffer);
        when(mockSource.read(mockByteBuffer)).thenReturn(-1);

        // Instantiate class under test
        UtowChunkedStream utowChunkedStream = new UtowChunkedStream(-1L);

        // Execute entry point method
        utowChunkedStream.send(mockSource, mockExchange, mockCallback);
    }
}
