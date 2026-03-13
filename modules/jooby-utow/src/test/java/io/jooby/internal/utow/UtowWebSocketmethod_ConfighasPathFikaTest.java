package io.jooby.internal.utow;

import com.typesafe.config.Config;
import io.jooby.Route;
import io.jooby.Router;
import io.undertow.websockets.core.WebSocketChannel;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class UtowWebSocketmethod_ConfighasPathFikaTest {

    @Test
    public void testConstructorTriggersConfigHasPath() {
        // Create mock dependencies
        UtowContext mockCtx = Mockito.mock(UtowContext.class);
        WebSocketChannel mockChannel = Mockito.mock(WebSocketChannel.class);
        Router mockRouter = Mockito.mock(Router.class);
        Config mockConfig = Mockito.mock(Config.class);
        Route mockRoute = Mockito.mock(Route.class);

        // Configure mock chain to reach Config.hasPath()
        when(mockCtx.getRouter()).thenReturn(mockRouter);
        when(mockRouter.getConfig()).thenReturn(mockConfig);
        when(mockCtx.getRoute()).thenReturn(mockRoute);
        when(mockRoute.getPattern()).thenReturn("/test");
        when(mockCtx.isInIoThread()).thenReturn(false);

        // This will trigger the call to conf.hasPath("websocket.maxSize")
        new UtowWebSocket(mockCtx, mockChannel);
    }
}
