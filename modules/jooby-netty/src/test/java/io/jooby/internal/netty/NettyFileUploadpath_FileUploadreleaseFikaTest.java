package io.jooby.internal.netty;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.DiskFileUpload;
import java.nio.file.Path;
import java.io.IOException;

import static org.mockito.Mockito.*;

class NettyFileUploadpath_FileUploadreleaseFikaTest {

    @TempDir
    Path tempDir;

    @Test
    void testPathTriggersRelease() throws IOException {
        FileUpload mockUpload = mock(FileUpload.class);
        when(mockUpload.isInMemory()).thenReturn(true);
        when(mockUpload.renameTo(any())).thenReturn(true);
        
        NettyFileUpload nettyFileUpload = new NettyFileUpload(tempDir, mockUpload);
        nettyFileUpload.path();
    }
}
