package io.jooby.internal.utow;

import io.jooby.SneakyThrows;
import io.undertow.server.handlers.form.FormData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class UtowFileUploaddestroy_FileItemdeleteFikaTest {

    @Test
    public void testDestroyCallsDelete() throws IOException {
        FormData.FileItem mockFileItem = Mockito.mock(FormData.FileItem.class);
        FormData.FormValue mockFormValue = Mockito.mock(FormData.FormValue.class);
        
        Mockito.when(mockFormValue.getFileItem()).thenReturn(mockFileItem);
        
        UtowFileUpload upload = new UtowFileUpload("test", mockFormValue);
        upload.destroy();
    }
}
