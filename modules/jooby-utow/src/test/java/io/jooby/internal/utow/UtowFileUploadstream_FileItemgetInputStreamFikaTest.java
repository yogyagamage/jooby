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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

class UtowFileUploadstream_FileItemgetInputStreamFikaTest {

    @Test
    void testStreamCallsGetInputStream() throws IOException {
        FileItem mockFileItem = Mockito.mock(FileItem.class);
        Mockito.when(mockFileItem.getInputStream()).thenReturn(new ByteArrayInputStream(new byte[0]));

        FormValue mockFormValue = Mockito.mock(FormValue.class);
        Mockito.when(mockFormValue.getFileItem()).thenReturn(mockFileItem);

        UtowFileUpload upload = new UtowFileUpload("test", mockFormValue);
        upload.stream();
    }
}
