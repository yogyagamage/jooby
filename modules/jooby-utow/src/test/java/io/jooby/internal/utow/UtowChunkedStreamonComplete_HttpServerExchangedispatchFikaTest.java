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

class UtowChunkedStreamonComplete_HttpServerExchangedispatchFikaTest {

    @Test
    void testOnCompleteTriggersDispatch() throws IOException {
        // Create mocks for dependencies
        ReadableByteChannel mockSource = mock(ReadableByteChannel.class);
        HttpServerExchange mockExchange = mock(HttpServerExchange.class);
        Sender mockSender = mock(Sender.class);
        IoCallback mockCallback = mock(IoCallback.class);
        ServerConnection mockConnection = mock(ServerConnection.class);
        ByteBufferPool mockBufferPool = mock(ByteBufferPool.class);
        PooledByteBuffer mockPooledBuffer = mock(PooledByteBuffer.class);
        ByteBuffer mockByteBuffer = mock(ByteBuffer.class);

        // Configure exchange to be in IO thread to trigger dispatch path
        when(mockExchange.isInIoThread()).thenReturn(true);
        when(mockExchange.getResponseSender()).thenReturn(mockSender);
        when(mockExchange.getConnection()).thenReturn(mockConnection);

        // Configure connection and buffer pool
        when(mockConnection.getByteBufferPool()).thenReturn(mockBufferPool);
        when(mockBufferPool.allocate()).thenReturn(mockPooledBuffer);
        when(mockPooledBuffer.getBuffer()).thenReturn(mockByteBuffer);

        // Configure source to return data to avoid immediate completion
        when(mockSource.read(mockByteBuffer)).thenReturn(1024);

        // Create instance with length that won't be exceeded immediately
        UtowChunkedStream stream = new UtowChunkedStream(2048L);

        // Call send which will trigger onComplete
        stream.send(mockSource, mockExchange, mockCallback);

        // The dispatch method should have been called once
        // No assertions or verifications per requirements
    }
}
