package io.jooby.internal.netty;

import io.jooby.SneakyThrows;
import io.netty.handler.codec.http.multipart.FileUpload;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.file.Path;
import java.nio.file.Paths;

class NettyFileUploadbytes_FileUploadgetFikaTest {

    @Test
    void test() throws Exception {
        Path basedir = Paths.get(".");
        FileUpload fileUpload = Mockito.mock(FileUpload.class);
        Mockito.when(fileUpload.isInMemory()).thenReturn(true);
        Mockito.when(fileUpload.get()).thenReturn(new byte[0]);

        NettyFileUpload nettyFileUpload = new NettyFileUpload(basedir, fileUpload);
        nettyFileUpload.bytes();
    }
}
