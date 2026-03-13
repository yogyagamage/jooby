package io.jooby.internal.netty;

import org.junit.jupiter.api.Test;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.DiskFileUpload;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NettyFileUploadgetContentType_FileUploadgetContentTypeFikaTest {

    @Test
    public void testGetContentType() throws Exception {
        Path basedir = Paths.get(System.getProperty("java.io.tmpdir"));
        FileUpload fileUpload = new DiskFileUpload("test", "test.txt", "text/plain", null, null, 0);
        NettyFileUpload nettyFileUpload = new NettyFileUpload(basedir, fileUpload);
        
        nettyFileUpload.getContentType();
    }
}
