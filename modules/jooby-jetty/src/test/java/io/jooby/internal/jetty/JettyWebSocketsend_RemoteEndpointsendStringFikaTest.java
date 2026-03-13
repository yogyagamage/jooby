package io.jooby.internal.jetty;

import io.jooby.Context;
import io.jooby.Route;
import io.jooby.Router;
import io.jooby.Server;
import io.jooby.SneakyThrows;
import io.jooby.WebSocket;
import io.jooby.WebSocket.OnClose;
import io.jooby.WebSocket.OnConnect;
import io.jooby.WebSocket.OnError;
import io.jooby.WebSocket.OnMessage;
import io.jooby.WebSocketCloseStatus;
import io.jooby.WebSocketConfigurer;
import io.jooby.WebSocketMessage;
import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WriteCallback;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

class JettyWebSocketsend_RemoteEndpointsendStringFikaTest {

    @Test
    void testSend() throws Exception {
        // Create mock dependencies
        JettyContext mockCtx = Mockito.mock(JettyContext.class);
        Router mockRouter = Mockito.mock(Router.class);
        Route mockRoute = Mockito.mock(Route.class);
        
        // Setup mock behavior
        Mockito.when(mockCtx.getRequestPath()).thenReturn("/test");
        Mockito.when(mockCtx.getRoute()).thenReturn(mockRoute);
        Mockito.when(mockRoute.getPattern()).thenReturn("/test");
        Mockito.when(mockCtx.getRouter()).thenReturn(mockRouter);
        
        // Create instance using constructor
        JettyWebSocket jettyWebSocket = new JettyWebSocket(mockCtx);
        
        // Create mock session and remote endpoint
        Session mockSession = Mockito.mock(Session.class);
        RemoteEndpoint mockRemoteEndpoint = Mockito.mock(RemoteEndpoint.class);
        
        // Setup mock behavior for session
        Mockito.when(mockSession.getRemote()).thenReturn(mockRemoteEndpoint);
        Mockito.when(mockSession.isOpen()).thenReturn(true);
        
        // Set the session on the instance (simulating onWebSocketConnect was called)
        jettyWebSocket.onWebSocketConnect(mockSession);
        
        // Call the entry point method
        jettyWebSocket.send("test message", false);
    }
}
