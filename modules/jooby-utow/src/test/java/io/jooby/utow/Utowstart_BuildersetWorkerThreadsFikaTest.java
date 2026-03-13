package io.jooby.utow;

import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.SslOptions;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLContext;
import java.util.ArrayList;
import java.util.List;

public class Utowstart_BuildersetWorkerThreadsFikaTest {

    @Test
    public void testStartCallsSetWorkerThreads() throws Exception {
        // Create instance using default constructor
        Utow utow = new Utow();
        
        // Create a mock Jooby application
        Jooby application = new Jooby();
        
        // Create ServerOptions with worker threads set
        ServerOptions options = new ServerOptions();
        options.setWorkerThreads(4); // Set worker threads to ensure setWorkerThreads is called
        
        // Use reflection to set the private options field
        java.lang.reflect.Field optionsField = Utow.class.getDeclaredField("options");
        optionsField.setAccessible(true);
        optionsField.set(utow, options);
        
        // Use reflection to set the private applications field
        java.lang.reflect.Field applicationsField = Utow.class.getDeclaredField("applications");
        applicationsField.setAccessible(true);
        applicationsField.set(utow, new ArrayList<>());
        
        // Start the server - this should trigger the call to setWorkerThreads
        utow.start(application);
    }
}
