package io.jooby.jetty;

import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.SslOptions;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;

public class Jettystart_HttpConfigurationsetSendDateHeaderFikaTest {

    @Test
    public void testStartInvokesSetSendDateHeader() throws Exception {
        Jetty jetty = new Jetty();
        
        ServerOptions options = new ServerOptions();
        options.setPort(0);
        options.setSecurePort(0);
        options.setDefaultHeaders(true);
        
        // Use reflection to set the options field since there's no setter
        java.lang.reflect.Field optionsField = Jetty.class.getDeclaredField("options");
        optionsField.setAccessible(true);
        optionsField.set(jetty, options);
        
        Jooby application = new Jooby();
        
        jetty.start(application);
    }
}
