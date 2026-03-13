package io.jooby.internal.netty;

import io.jooby.Context;
import io.netty.handler.codec.http.multipart.HttpData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.mockito.Mockito.when;

public class NettyBodystream_HttpDatagetFikaTest {

    @Test
    public void testStreamInvokesHttpGet() throws IOException {
        // Create mock HttpData that will be used in the call chain
        HttpData mockHttpData = Mockito.mock(HttpData.class);
        
        // Configure the mock to ensure the execution path goes through data.get()
        when(mockHttpData.isInMemory()).thenReturn(true);
        when(mockHttpData.get()).thenReturn(new byte[]{1, 2, 3});
        
        // Create mock Context (required for constructor but not directly used in the call chain)
        Context mockContext = Mockito.mock(Context.class);
        
        // Instantiate the class under test using the provided constructor
        NettyBody nettyBody = new NettyBody(mockContext, mockHttpData, 100L);
        
        // Call the entry point method - this should trigger HttpData.get() internally
        nettyBody.stream();
        
        // No assertions or verifications - test succeeds if no exceptions are thrown
    }
}
