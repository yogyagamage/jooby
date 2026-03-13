package io.jooby.utow;

import io.jooby.Environment;
import io.jooby.Http2Configurer;
import io.jooby.Jooby;
import io.jooby.Router;
import io.jooby.Server;
import io.jooby.ServerOptions;
import io.jooby.SneakyThrows;
import io.jooby.SslOptions;
import io.jooby.internal.utow.UtowHandler;
import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import io.undertow.server.HttpHandler;
import io.undertow.server.handlers.HttpContinueReadHandler;
import io.undertow.server.handlers.encoding.ContentEncodingProvider;
import io.undertow.server.handlers.encoding.ContentEncodingRepository;
import io.undertow.server.handlers.encoding.DeflateEncodingProvider;
import io.undertow.server.handlers.encoding.EncodingHandler;
import io.undertow.server.handlers.encoding.GzipEncodingProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.net.ssl.SSLContext;
import org.xnio.Option;
import org.xnio.Options;
import org.xnio.Sequence;
import org.xnio.SslClientAuthMode;
import org.xnio.XnioWorker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.Spliterator;
import java.util.concurrent.Executor;

import static java.util.stream.StreamSupport.stream;

public class Utowstart_BuildersetServerOptionFikaTest {

    @Test
    public void testStart() {
        // Create instance of Utow using the provided constructor
        Utow utow = new Utow();
        
        // Create a mock Jooby application
        Jooby mockApplication = Mockito.mock(Jooby.class);
        Environment mockEnvironment = Mockito.mock(Environment.class);
        ClassLoader mockClassLoader = Mockito.mock(ClassLoader.class);
        
        // Setup the mock application to return required dependencies
        Mockito.when(mockApplication.getEnvironment()).thenReturn(mockEnvironment);
        Mockito.when(mockEnvironment.getClassLoader()).thenReturn(mockClassLoader);
        
        // Create a mock Executor to avoid NPE in fireStart
        Executor mockExecutor = Mockito.mock(Executor.class);
        Mockito.when(mockApplication.setDefaultWorker(Mockito.any(Executor.class))).thenReturn(mockApplication);
        
        // Call the entry point method
        utow.start(mockApplication);
    }
}
