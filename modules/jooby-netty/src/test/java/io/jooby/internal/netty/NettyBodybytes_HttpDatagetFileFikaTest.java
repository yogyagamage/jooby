package io.jooby.internal.netty;

import io.jooby.Context;
import io.netty.handler.codec.http.multipart.HttpData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.mockito.Mockito.when;

public class NettyBodybytes_HttpDatagetFileFikaTest {

    @Test
    public void testBytesCallsGetFile() throws IOException {
        // Create mock HttpData that will trigger the getFile() call
        HttpData mockHttpData = Mockito.mock(HttpData.class);
        
        // Configure the mock to NOT be in memory, so execution goes to getFile()
        when(mockHttpData.isInMemory()).thenReturn(false);
        
        // Create a temporary file for getFile() to return
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();
        
        // Write some content to the file so Files.readAllBytes has something to read
        Files.write(tempFile.toPath(), "test content".getBytes());
        
        // Configure the mock to return the temp file when getFile() is called
        when(mockHttpData.getFile()).thenReturn(tempFile);
        
        // Create mock Context (required for constructor but not used in this path)
        Context mockContext = Mockito.mock(Context.class);
        
        // Instantiate NettyBody using the provided constructor
        NettyBody nettyBody = new NettyBody(mockContext, mockHttpData, 100L);
        
        // Call the entry point method - this should trigger the chain ending with getFile()
        nettyBody.bytes();
    }
}
