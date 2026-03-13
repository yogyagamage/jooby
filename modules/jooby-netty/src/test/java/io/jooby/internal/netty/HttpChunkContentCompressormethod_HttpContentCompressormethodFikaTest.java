package io.jooby.internal.netty;

import io.netty.handler.codec.http.HttpContentCompressor;
import org.junit.jupiter.api.Test;

public class HttpChunkContentCompressormethod_HttpContentCompressormethodFikaTest {

    @Test
    public void test() {
        int compressionLevel = 1;
        HttpChunkContentCompressor instance = new HttpChunkContentCompressor(compressionLevel);
    }
}
