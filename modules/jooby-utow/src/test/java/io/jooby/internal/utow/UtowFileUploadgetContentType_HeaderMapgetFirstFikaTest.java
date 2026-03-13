package io.jooby.internal.utow;

import io.jooby.SneakyThrows;
import io.undertow.server.handlers.form.FormData;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class UtowFileUploadgetContentType_HeaderMapgetFirstFikaTest {

    @Test
    public void test() throws IOException {
        // Create mock FormData.FormValue
        FormData.FormValue mockFormValue = Mockito.mock(FormData.FormValue.class);
        
        // Create mock HeaderMap that will be returned by upload.getHeaders()
        HeaderMap mockHeaderMap = Mockito.mock(HeaderMap.class);
        Mockito.when(mockFormValue.getHeaders()).thenReturn(mockHeaderMap);
        
        // Create the UtowFileUpload instance
        UtowFileUpload instance = new UtowFileUpload("testName", mockFormValue);
        
        // Invoke the entry point method
        instance.getContentType();
        
        // The call chain will be:
        // 1. instance.getContentType()
        // 2. upload.getHeaders() returns mockHeaderMap
        // 3. mockHeaderMap.getFirst(Headers.CONTENT_TYPE) - target third-party method
    }
}
