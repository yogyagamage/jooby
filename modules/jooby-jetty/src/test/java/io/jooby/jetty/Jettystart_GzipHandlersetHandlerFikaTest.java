package io.jooby.jetty;

import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.SslOptions;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;

public class Jettystart_GzipHandlersetHandlerFikaTest {

    @Test
    public void testStart() throws Exception {
        Jetty jetty = new Jetty();
        
        ServerOptions options = new ServerOptions();
        options.setCompressionLevel(1);
        options.setPort(0);
        options.setSecurePort(0);
        
        // Use reflection to set private field
        java.lang.reflect.Field optionsField = Jetty.class.getDeclaredField("options");
        optionsField.setAccessible(true);
        optionsField.set(jetty, options);
        
        Jooby jooby = new Jooby();
        
        // Start the server - this should trigger the GzipHandler.setHandler call
        jetty.start(jooby);
        
        // Clean up to avoid resource leaks
        jetty.stop();
    }
}
