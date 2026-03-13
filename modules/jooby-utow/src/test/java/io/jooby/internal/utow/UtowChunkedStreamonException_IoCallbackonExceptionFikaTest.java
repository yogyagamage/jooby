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

public class UtowChunkedStreamonException_IoCallbackonExceptionFikaTest {

    @Test
    public void test() throws IOException {
        UtowChunkedStream instance = new UtowChunkedStream(-1L);
        
        ReadableByteChannel mockSource = Mockito.mock(ReadableByteChannel.class);
        HttpServerExchange mockExchange = Mockito.mock(HttpServerExchange.class);
        IoCallback mockCallback = Mockito.mock(IoCallback.class);
        Sender mockSender = Mockito.mock(Sender.class);
        ServerConnection mockConnection = Mockito.mock(ServerConnection.class);
        ByteBufferPool mockBufferPool = Mockito.mock(ByteBufferPool.class);
        PooledByteBuffer mockPooled = Mockito.mock(PooledByteBuffer.class);
        
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Mockito.when(mockPooled.getBuffer()).thenReturn(buffer);
        Mockito.when(mockConnection.getByteBufferPool()).thenReturn(mockBufferPool);
        Mockito.when(mockBufferPool.allocate()).thenReturn(mockPooled);
        Mockito.when(mockExchange.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockExchange.getResponseSender()).thenReturn(mockSender);
        Mockito.when(mockSource.read(buffer)).thenThrow(new IOException("Test exception"));
        
        instance.send(mockSource, mockExchange, mockCallback);
    }
}
