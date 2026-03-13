package io.jooby.internal.netty;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpData;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import org.junit.jupiter.api.Test;

class HttpRawPostRequestDecoderdestroy_HttpDatadeleteFikaTest {

    @Test
    void testDestroyCallsDelete() {
        HttpDataFactory factory = new DefaultHttpDataFactory(false);
        DefaultHttpRequest request = new DefaultHttpRequest(
            HttpVersion.HTTP_1_1, 
            HttpMethod.POST, 
            "/test"
        );
        
        HttpRawPostRequestDecoder decoder = new HttpRawPostRequestDecoder(factory, request);
        decoder.destroy();
    }
}
