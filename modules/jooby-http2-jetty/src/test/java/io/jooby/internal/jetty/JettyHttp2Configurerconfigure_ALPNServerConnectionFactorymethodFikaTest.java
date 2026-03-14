package io.jooby.internal.jetty;

import org.eclipse.jetty.alpn.server.ALPNServerConnectionFactory;
import org.eclipse.jetty.http2.server.HTTP2CServerConnectionFactory;
import org.eclipse.jetty.http2.server.HTTP2ServerConnectionFactory;
import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.NegotiatingServerConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class JettyHttp2Configurerconfigure_ALPNServerConnectionFactorymethodFikaTest {

    @Test
    void testConfigure() {
        JettyHttp2Configurer configurer = new JettyHttp2Configurer();
        
        HttpConfiguration httpConfig = new HttpConfiguration();
        SecureRequestCustomizer secureCustomizer = new SecureRequestCustomizer();
        httpConfig.addCustomizer(secureCustomizer);
        
        List<ConnectionFactory> result = configurer.configure(httpConfig);
    }
}
