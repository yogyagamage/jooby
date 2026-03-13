package io.jooby.internal.netty;

import io.jooby.Context;
import io.netty.handler.codec.http.multipart.HttpData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.when;

public class NettyBodystream_HttpDatagetFileFikaTest {

    @Test
    public void testStreamCallsGetFile() throws IOException {
        // Create mock HttpData that will trigger the getFile() path
        HttpData mockHttpData = Mockito.mock(HttpData.class);
        when(mockHttpData.isInMemory()).thenReturn(false);
        
        // Create a temporary file for getFile() to return
        File tempFile = File.createTempFile("test", ".tmp");
        tempFile.deleteOnExit();
        when(mockHttpData.getFile()).thenReturn(tempFile);
        
        // Create mock Context (required for constructor but not used in the path)
        Context mockContext = Mockito.mock(Context.class);
        
        // Instantiate NettyBody using the provided constructor
        NettyBody nettyBody = new NettyBody(mockContext, mockHttpData, 0L);
        
        // Call the entry point method - this should trigger HttpData.getFile()
        nettyBody.stream();
        
        // Clean up
        tempFile.delete();
    }
}
