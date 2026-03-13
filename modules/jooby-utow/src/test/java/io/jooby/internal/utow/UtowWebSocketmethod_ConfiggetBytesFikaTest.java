package io.jooby.internal.utow;

import com.typesafe.config.Config;
import io.jooby.Route;
import io.jooby.Router;
import io.undertow.websockets.core.WebSocketChannel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowWebSocketmethod_ConfiggetBytesFikaTest {

    @Test
    public void test() {
        // Create mocks for constructor dependencies
        UtowContext ctx = Mockito.mock(UtowContext.class);
        WebSocketChannel channel = Mockito.mock(WebSocketChannel.class);
        
        // Create mock for Route
        Route route = Mockito.mock(Route.class);
        when(ctx.getRoute()).thenReturn(route);
        when(route.getPattern()).thenReturn("/test");
        
        // Create mock for Router
        Router router = Mockito.mock(Router.class);
        when(ctx.getRouter()).thenReturn(router);
        
        // Create mock for Config that will be called by the constructor
        Config config = Mockito.mock(Config.class);
        when(router.getConfig()).thenReturn(config);
        
        // Setup the path to trigger the target method call
        when(config.hasPath("websocket.maxSize")).thenReturn(true);
        // This is the target third-party method call we want to execute
        when(config.getBytes("websocket.maxSize")).thenReturn(java.lang.Long.valueOf(1024L));
        
        // Instantiate the class under test using the provided constructor
        // This will execute the constructor and trigger the Config.getBytes call
        UtowWebSocket websocket = new UtowWebSocket(ctx, channel);
    }
}
