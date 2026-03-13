package io.jooby.internal.utow;

import io.jooby.SneakyThrows;
import io.undertow.server.handlers.form.FormData;
import io.undertow.server.handlers.form.FormData.FileItem;
import io.undertow.server.handlers.form.FormData.FormValue;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UtowFileUploadgetFileName_FormValuegetFileNameFikaTest {

    @Test
    public void testGetFileName() throws IOException {
        FormData.FormValue formValue = mock(FormData.FormValue.class);
        when(formValue.getFileName()).thenReturn("test.txt");
        
        UtowFileUpload upload = new UtowFileUpload("fieldName", formValue);
        upload.getFileName();
    }
}
