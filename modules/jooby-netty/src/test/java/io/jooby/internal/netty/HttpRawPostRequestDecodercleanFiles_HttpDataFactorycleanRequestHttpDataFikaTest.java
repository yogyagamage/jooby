package io.jooby.internal.netty;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

public class HttpRawPostRequestDecodercleanFiles_HttpDataFactorycleanRequestHttpDataFikaTest {

    @Test
    public void testCleanFiles() {
        HttpDataFactory factory = mock(HttpDataFactory.class);
        HttpRequest request = mock(HttpRequest.class);
        
        HttpRawPostRequestDecoder decoder = new HttpRawPostRequestDecoder(factory, request);
        decoder.cleanFiles();
    }
}
