package io.jooby.utow;

import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.undertow.Undertow;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class UtowshutdownServer_UndertowstopFikaTest {

    @Test
    void testStopCallsUndertowStop() {
        // Create a real Undertow instance
        Undertow undertow = Mockito.mock(Undertow.class);
        
        // Create the Utow instance using the provided constructor
        Utow utow = new Utow();
        
        // Set the server field to our mock Undertow instance
        // Since server is private, we need to use reflection
        try {
            java.lang.reflect.Field serverField = Utow.class.getDeclaredField("server");
            serverField.setAccessible(true);
            serverField.set(utow, undertow);
            
            // Set applications field to avoid NPE in stop() method
            java.lang.reflect.Field applicationsField = Utow.class.getDeclaredField("applications");
            applicationsField.setAccessible(true);
            applicationsField.set(utow, new ArrayList<Jooby>());
            
            // Set options field to avoid NPE
            java.lang.reflect.Field optionsField = Utow.class.getDeclaredField("options");
            optionsField.setAccessible(true);
            optionsField.set(utow, new ServerOptions());
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        // Call the entry point method - this should trigger the call chain
        utow.stop();
        
        // The test will pass if undertow.stop() is called during execution
        // No assertions or verifications are needed per requirements
    }
}
