package io.jooby.internal.netty;

import io.jooby.SneakyThrows;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.ByteBufInputStream;
import io.netty.handler.codec.http.multipart.DiskFileUpload;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.HttpData;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.util.ReferenceCounted;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.InputStream;
import java.nio.file.Path;

class NettyFileUploadstream_ByteBufInputStreammethodFikaTest {

    @Test
    void test() throws Exception {
        Path basedir = Mockito.mock(Path.class);
        FileUpload upload = Mockito.mock(FileUpload.class);
        ByteBuf byteBuf = Mockito.mock(ByteBuf.class);
        
        Mockito.when(upload.isInMemory()).thenReturn(true);
        Mockito.when(upload.content()).thenReturn(byteBuf);
        
        NettyFileUpload nettyFileUpload = new NettyFileUpload(basedir, upload);
        InputStream result = nettyFileUpload.stream();
    }
}
