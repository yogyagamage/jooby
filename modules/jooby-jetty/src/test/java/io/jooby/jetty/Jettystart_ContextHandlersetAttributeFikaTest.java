package io.jooby.jetty;

import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.SslOptions;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLContext;
import java.lang.reflect.Method;

public class Jettystart_ContextHandlersetAttributeFikaTest {

    @Test
    public void testStart() throws Exception {
        Jetty jetty = new Jetty();
        
        ServerOptions options = new ServerOptions();
        options.setPort(0);
        options.setSecurePort(0);
        
        // Set SSL options to avoid SSL-related exceptions
        SslOptions sslOptions = new SslOptions();
        // Create a minimal SSLContext
        SSLContext sslContext = SSLContext.getDefault();
        // Use reflection to set SSLContext since setSSLContext method might not be public
        try {
            Method setSslContextMethod = ServerOptions.class.getDeclaredMethod("setSSLContext", SSLContext.class);
            setSslContextMethod.setAccessible(true);
            setSslContextMethod.invoke(options, sslContext);
        } catch (NoSuchMethodException e) {
            // If method doesn't exist, we'll proceed without SSL
        }
        
        // Use reflection to set options field
        try {
            java.lang.reflect.Field optionsField = Jetty.class.getDeclaredField("options");
            optionsField.setAccessible(true);
            optionsField.set(jetty, options);
        } catch (NoSuchFieldException e) {
            // Field should exist based on provided code
        }
        
        // Create a minimal Jooby application
        Jooby app = new Jooby();
        
        // Start the server
        jetty.start(app);
        
        // Stop the server to clean up resources
        try {
            java.lang.reflect.Field serverField = Jetty.class.getDeclaredField("server");
            serverField.setAccessible(true);
            org.eclipse.jetty.server.Server server = 
                (org.eclipse.jetty.server.Server) serverField.get(jetty);
            if (server != null) {
                server.stop();
            }
        } catch (Exception e) {
            // Ignore cleanup errors
        }
    }
}
