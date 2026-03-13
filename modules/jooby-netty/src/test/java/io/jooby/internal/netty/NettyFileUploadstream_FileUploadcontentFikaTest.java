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

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NettyFileUploadstream_FileUploadcontentFikaTest {

    @Test
    public void testStreamCallsContent() throws Exception {
        Path basedir = Files.createTempDirectory("nettytest");
        FileUpload fileUpload = mock(FileUpload.class);
        
        when(fileUpload.isInMemory()).thenReturn(true);
        when(fileUpload.content()).thenReturn(mock(ByteBuf.class));
        
        NettyFileUpload nettyFileUpload = new NettyFileUpload(basedir, fileUpload);
        
        try (InputStream ignored = nettyFileUpload.stream()) {
            // No assertions - just executing the chain
        } finally {
            try {
                nettyFileUpload.destroy();
            } catch (Exception e) {
                // Ignore cleanup errors
            }
            Files.deleteIfExists(basedir);
        }
    }
}
