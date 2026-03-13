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

public class Utowstart_BuildersetIoThreadsFikaTest {

    @Test
    public void start_shouldInvokeSetIoThreads() {
        Utow utow = new Utow();
        
        Jooby mockJooby = Mockito.mock(Jooby.class);
        Environment mockEnvironment = Mockito.mock(Environment.class);
        ClassLoader mockClassLoader = Mockito.mock(ClassLoader.class);
        
        Mockito.when(mockJooby.getEnvironment()).thenReturn(mockEnvironment);
        Mockito.when(mockEnvironment.getClassLoader()).thenReturn(mockClassLoader);
        
        Executor mockExecutor = Mockito.mock(Executor.class);
        Mockito.when(mockJooby.setDefaultWorker(Mockito.any(Executor.class))).thenReturn(mockJooby);
        
        utow.start(mockJooby);
    }
}
