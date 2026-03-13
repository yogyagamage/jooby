package io.jooby.internal.netty;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import org.junit.jupiter.api.Test;

class HttpRawPostRequestDecoderremoveHttpDataFromClean_HttpDataFactoryremoveHttpDataFromCleanFikaTest {

    @Test
    void test() {
        HttpDataFactory factory = new DefaultHttpDataFactory();
        HttpRequest request = new DefaultHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, "/");
        HttpRawPostRequestDecoder decoder = new HttpRawPostRequestDecoder(factory, request);
        
        InterfaceHttpData data = factory.createAttribute(request, "test");
        decoder.removeHttpDataFromClean(data);
    }
}
