package io.jooby.jetty;

import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.SslOptions;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLContext;
import java.lang.reflect.Method;

public class Jettystart_HttpConfigurationsetOutputBufferSizeFikaTest {

    @Test
    public void testStart() throws Exception {
        Jetty jetty = new Jetty();
        
        ServerOptions options = new ServerOptions();
        options.setBufferSize(8192);
        options.setPort(0);
        options.setSecurePort(0);
        
        // Set options using reflection since there's no setter
        Method setOptionsMethod = Jetty.class.getDeclaredMethod("setOptions", ServerOptions.class);
        setOptionsMethod.setAccessible(true);
        setOptionsMethod.invoke(jetty, options);
        
        Jooby application = new Jooby();
        
        // Start the server - this should trigger the HttpConfiguration.setOutputBufferSize call
        jetty.start(application);
        
        // Clean up
        jetty.stop();
    }
}
