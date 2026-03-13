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
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.Spliterator;
import java.util.concurrent.Executor;

import static java.util.stream.StreamSupport.stream;

public class Utowstart_EncodingHandlermethodFikaTest {

    @Test
    public void testStartMethodTriggersEncodingHandlerConstructor() {
        // Create Utow instance
        Utow utow = new Utow();
        
        // Create a mock Jooby application
        Jooby mockApplication = Mockito.mock(Jooby.class);
        Environment mockEnvironment = Mockito.mock(Environment.class);
        ClassLoader mockClassLoader = Mockito.mock(ClassLoader.class);
        
        // Setup mock behavior for the application
        Mockito.when(mockApplication.getEnvironment()).thenReturn(mockEnvironment);
        Mockito.when(mockEnvironment.getClassLoader()).thenReturn(mockClassLoader);
        
        // Create a mock Executor for the worker
        Executor mockExecutor = Mockito.mock(Executor.class);
        
        // Setup the application to return the mock executor when setDefaultWorker is called
        Mockito.when(mockApplication.setDefaultWorker(Mockito.any(Executor.class))).thenReturn(mockApplication);
        
        // Configure ServerOptions to enable compression (so EncodingHandler is created)
        ServerOptions options = new ServerOptions();
        options.setCompressionLevel(1); // Set compression level to trigger EncodingHandler creation
        options.setPort(8080);
        options.setHost("localhost");
        options.setIoThreads(1);
        options.setWorkerThreads(1);
        options.setDefaultHeaders(true);
        options.setBufferSize(1024);
        options.setMaxRequestSize(1024 * 1024);
        options.setHttpsOnly(false);
        options.setHttp2(false); // Disable HTTP2 to avoid ServiceLoader complexity
        
        // Use reflection to set the options field since there's no setter
        try {
            java.lang.reflect.Field optionsField = Utow.class.getDeclaredField("options");
            optionsField.setAccessible(true);
            optionsField.set(utow, options);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        // Call the entry point method
        utow.start(mockApplication);
    }
}
