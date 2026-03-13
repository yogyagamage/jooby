package io.jooby.internal.utow;

import io.undertow.connector.ByteBufferPool;
import io.undertow.connector.PooledByteBuffer;
import io.undertow.io.IoCallback;
import io.undertow.io.Sender;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.ServerConnection;
import org.xnio.IoUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

public class UtowChunkedStreamdone_PooledByteBuffercloseFikaTest {

    @Test
    public void test() throws IOException {
        UtowChunkedStream instance = new UtowChunkedStream(-1L);
        
        ReadableByteChannel mockSource = Mockito.mock(ReadableByteChannel.class);
        HttpServerExchange mockExchange = Mockito.mock(HttpServerExchange.class);
        IoCallback mockCallback = Mockito.mock(IoCallback.class);
        Sender mockSender = Mockito.mock(Sender.class);
        ServerConnection mockConnection = Mockito.mock(ServerConnection.class);
        ByteBufferPool mockByteBufferPool = Mockito.mock(ByteBufferPool.class);
        PooledByteBuffer mockPooledByteBuffer = Mockito.mock(PooledByteBuffer.class);
        
        ByteBuffer mockBuffer = ByteBuffer.allocate(1024);
        Mockito.when(mockPooledByteBuffer.getBuffer()).thenReturn(mockBuffer);
        Mockito.when(mockByteBufferPool.allocate()).thenReturn(mockPooledByteBuffer);
        Mockito.when(mockConnection.getByteBufferPool()).thenReturn(mockByteBufferPool);
        Mockito.when(mockExchange.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockExchange.getResponseSender()).thenReturn(mockSender);
        
        instance.send(mockSource, mockExchange, mockCallback);
        
        IOException testException = new IOException("Test exception");
        instance.onException(mockExchange, mockSender, testException);
    }
}
