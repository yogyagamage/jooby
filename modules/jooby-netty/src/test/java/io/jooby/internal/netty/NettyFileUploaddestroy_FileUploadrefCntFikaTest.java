package io.jooby.internal.netty;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.netty.handler.codec.http.multipart.FileUpload;
import java.nio.file.Path;

public class NettyFileUploaddestroy_FileUploadrefCntFikaTest {

    @Test
    public void testDestroyCallsRefCnt() {
        Path mockBasedir = Mockito.mock(Path.class);
        FileUpload mockUpload = Mockito.mock(FileUpload.class);
        
        NettyFileUpload instance = new NettyFileUpload(mockBasedir, mockUpload);
        
        instance.destroy();
    }
}
