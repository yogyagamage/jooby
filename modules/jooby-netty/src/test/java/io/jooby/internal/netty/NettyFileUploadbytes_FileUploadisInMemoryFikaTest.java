package io.jooby.internal.netty;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import io.netty.handler.codec.http.multipart.FileUpload;
import java.nio.file.Path;

public class NettyFileUploadbytes_FileUploadisInMemoryFikaTest {

    @Test
    public void test() throws Exception {
        Path basedir = Mockito.mock(Path.class);
        FileUpload fileUpload = Mockito.mock(FileUpload.class);
        
        Mockito.when(fileUpload.isInMemory()).thenReturn(true);
        Mockito.when(fileUpload.get()).thenReturn(new byte[0]);
        
        NettyFileUpload nettyFileUpload = new NettyFileUpload(basedir, fileUpload);
        nettyFileUpload.bytes();
    }
}
