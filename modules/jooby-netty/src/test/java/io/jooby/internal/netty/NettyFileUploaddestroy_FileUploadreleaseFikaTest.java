package io.jooby.internal.netty;

import io.jooby.SneakyThrows;
import io.netty.handler.codec.http.multipart.FileUpload;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.file.Path;

import static org.mockito.Mockito.when;

public class NettyFileUploaddestroy_FileUploadreleaseFikaTest {

    @Test
    public void testDestroyCallsRelease() {
        // Create mock FileUpload with refCnt > 0
        FileUpload mockUpload = Mockito.mock(FileUpload.class);
        when(mockUpload.refCnt()).thenReturn(1);
        
        // Create mock Path for basedir
        Path mockBasedir = Mockito.mock(Path.class);
        
        // Instantiate NettyFileUpload
        NettyFileUpload nettyFileUpload = new NettyFileUpload(mockBasedir, mockUpload);
        
        // Call destroy() which should trigger upload.release()
        nettyFileUpload.destroy();
    }
}
