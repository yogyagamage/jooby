package io.jooby.internal.utow;

import io.jooby.Body;
import io.jooby.Context;
import io.jooby.DefaultContext;
import io.jooby.Router;
import io.jooby.Router.Match;
import io.jooby.StatusCode;
import io.jooby.exception.StatusCodeException;
import io.undertow.server.ExchangeCompletionListener;
import io.undertow.server.HttpServerExchange;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

public class UtowBodyHandlerhandle_HttpServerExchangeaddExchangeCompleteListenerFikaTest {

    @Test
    public void test() throws IOException {
        // Create mock dependencies for constructor
        Router.Match mockRoute = Mockito.mock(Router.Match.class);
        UtowContext mockContext = Mockito.mock(UtowContext.class);
        Router mockRouter = Mockito.mock(Router.class);
        Path mockTmpdir = Mockito.mock(Path.class);
        Path mockFile = Mockito.mock(Path.class);
        FileChannel mockFileChannel = Mockito.mock(FileChannel.class);

        // Setup mock context to return router and tmpdir
        Mockito.when(mockContext.getRouter()).thenReturn(mockRouter);
        Mockito.when(mockRouter.getTmpdir()).thenReturn(mockTmpdir);
        Mockito.when(mockTmpdir.resolve(Mockito.anyString())).thenReturn(mockFile);
        Mockito.when(mockFileChannel.write(Mockito.any(ByteBuffer.class), Mockito.anyLong()))
                .thenReturn(0);

        // Create real HttpServerExchange
        HttpServerExchange exchange = new HttpServerExchange(null);

        // Create instance of class under test
        int bufferSize = 1024;
        long maxRequestSize = 8192;
        UtowBodyHandler handler = new UtowBodyHandler(mockRoute, mockContext, bufferSize, maxRequestSize);

        // Use reflection to set private fields to trigger the desired path
        try {
            // Set file and channel to non-null to enter the branch where addExchangeCompleteListener is called
            java.lang.reflect.Field fileField = UtowBodyHandler.class.getDeclaredField("file");
            fileField.setAccessible(true);
            fileField.set(handler, mockFile);

            java.lang.reflect.Field channelField = UtowBodyHandler.class.getDeclaredField("channel");
            channelField.setAccessible(true);
            channelField.set(handler, mockFileChannel);

            // Set chunkSize > bufferSize to ensure we're in the overflow path
            java.lang.reflect.Field chunkSizeField = UtowBodyHandler.class.getDeclaredField("chunkSize");
            chunkSizeField.setAccessible(true);
            chunkSizeField.set(handler, bufferSize + 1L);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Call the entry point with last=true to trigger addExchangeCompleteListener
        byte[] chunk = new byte[]{1, 2, 3};
        handler.handle(exchange, chunk, true);
    }
}
