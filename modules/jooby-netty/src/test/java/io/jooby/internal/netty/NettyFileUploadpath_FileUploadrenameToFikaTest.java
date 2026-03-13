package io.jooby.internal.netty;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.io.File;
import java.io.IOException;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.DiskFileUpload;

import static org.mockito.Mockito.*;

class NettyFileUploadpath_FileUploadrenameToFikaTest {

    @TempDir
    Path tempDir;

    @Test
    void testPathCallsRenameTo() throws IOException {
        FileUpload mockUpload = mock(FileUpload.class);
        when(mockUpload.isInMemory()).thenReturn(true);
        when(mockUpload.getFile()).thenReturn(new File("dummy.txt"));
        
        NettyFileUpload nettyFileUpload = new NettyFileUpload(tempDir, mockUpload);
        nettyFileUpload.path();
    }
}
