package io.jooby.internal.netty;

import org.junit.jupiter.api.Test;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.DiskFileUpload;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NettyFileUploadgetName_FileUploadgetNameFikaTest {

    @Test
    public void testGetName() throws Exception {
        Path basedir = Paths.get(".");
        FileUpload fileUpload = new DiskFileUpload("test", "test.txt", "text/plain", null, null, 0);
        NettyFileUpload nettyFileUpload = new NettyFileUpload(basedir, fileUpload);
        nettyFileUpload.getName();
    }
}
