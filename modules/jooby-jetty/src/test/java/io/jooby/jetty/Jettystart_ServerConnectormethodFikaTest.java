package io.jooby.jetty;

import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.SslOptions;
import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLContext;
import java.util.ArrayList;
import java.util.List;

public class Jettystart_ServerConnectormethodFikaTest {

    @Test
    public void testStartMethodInvokesServerConnectorConstructor() throws Exception {
        Jetty jetty = new Jetty();
        
        ServerOptions options = new ServerOptions();
        options.setPort(8080);
        options.setHost("localhost");
        options.setHttpsOnly(false);
        
        jetty.setOptions(options);
        
        Jooby jooby = new Jooby();
        
        jetty.start(jooby);
    }
}
