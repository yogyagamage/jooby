package io.jooby.internal.utow;

import io.jooby.SneakyThrows;
import io.undertow.server.handlers.form.FormData;
import io.undertow.server.handlers.form.FormData.FileItem;
import io.undertow.server.handlers.form.FormData.FormValue;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

class UtowFileUploadgetContentType_FormValuegetHeadersFikaTest {

    @Test
    void test() throws IOException {
        FormData.FormValue formValue = Mockito.mock(FormData.FormValue.class);
        HeaderMap headerMap = Mockito.mock(HeaderMap.class);
        
        Mockito.when(formValue.getHeaders()).thenReturn(headerMap);
        Mockito.when(headerMap.getFirst(Headers.CONTENT_TYPE)).thenReturn("text/plain");
        
        UtowFileUpload upload = new UtowFileUpload("test.txt", formValue);
        upload.getContentType();
    }
}
