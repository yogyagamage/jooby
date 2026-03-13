package io.jooby.internal.netty;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.Attribute;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class HttpRawPostRequestDecodermethod_HttpDataFactorycreateAttributeFikaTest {

    @Test
    void testCreateAttributeIsCalled() {
        HttpDataFactory factory = mock(HttpDataFactory.class);
        HttpRequest request = mock(HttpRequest.class);
        Attribute attribute = mock(Attribute.class);
        
        when(factory.createAttribute(request, "body")).thenReturn(attribute);
        
        new HttpRawPostRequestDecoder(factory, request);
    }
}
