package io.jooby.jetty;

import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.Test;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.internal.jetty.JettyHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import javax.servlet.ServletContext;
import java.util.ArrayList;

public class Jettystop_ServerstopFikaTest {

    @Test
    public void testStop() throws Exception {
        Jetty jetty = new Jetty();
        
        // Create a real Jetty Server instance
        QueuedThreadPool threadPool = new QueuedThreadPool();
        Server server = new Server(threadPool);
        
        // Set up minimal configuration to allow stop() to work
        server.setHandler(new ContextHandler());
        server.start();
        
        // Use reflection to set the private server field
        java.lang.reflect.Field serverField = Jetty.class.getDeclaredField("server");
        serverField.setAccessible(true);
        serverField.set(jetty, server);
        
        // Call the entry point
        jetty.stop();
    }
}
