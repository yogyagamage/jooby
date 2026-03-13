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

public class UtowChunkedStreamsend_HttpServerExchangegetResponseSenderFikaTest {

    @Test
    public void testSendCallsGetResponseSender() throws IOException {
        // Create mocks for dependencies
        HttpServerExchange exchange = mock(HttpServerExchange.class);
        ReadableByteChannel source = mock(ReadableByteChannel.class);
        IoCallback callback = mock(IoCallback.class);
        Sender sender = mock(Sender.class);
        ServerConnection connection = mock(ServerConnection.class);
        ByteBufferPool byteBufferPool = mock(ByteBufferPool.class);
        PooledByteBuffer pooledByteBuffer = mock(PooledByteBuffer.class);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // Setup mock behavior
        when(exchange.getResponseSender()).thenReturn(sender);
        when(exchange.getConnection()).thenReturn(connection);
        when(connection.getByteBufferPool()).thenReturn(byteBufferPool);
        when(byteBufferPool.allocate()).thenReturn(pooledByteBuffer);
        when(pooledByteBuffer.getBuffer()).thenReturn(byteBuffer);
        when(source.read(byteBuffer)).thenReturn(-1);

        // Create instance using constructor
        UtowChunkedStream utowChunkedStream = new UtowChunkedStream(-1L);

        // Call the entry point method
        utowChunkedStream.send(source, exchange, callback);
    }
}
