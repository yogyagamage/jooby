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
import org.slf4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

class JettyWebSockethandleClose_SessioncloseFikaTest {

    @Test
    void testClosePath() throws Exception {
        // Create mock dependencies for JettyContext constructor
        JettyContext mockCtx = Mockito.mock(JettyContext.class);
        Route mockRoute = Mockito.mock(Route.class);
        Router mockRouter = Mockito.mock(Router.class);
        Logger mockLogger = Mockito.mock(Logger.class);
        
        Mockito.when(mockCtx.getRoute()).thenReturn(mockRoute);
        Mockito.when(mockRoute.getPattern()).thenReturn("test-key");
        Mockito.when(mockCtx.getRequestPath()).thenReturn("/test-path");
        Mockito.when(mockCtx.getRouter()).thenReturn(mockRouter);
        Mockito.when(mockRouter.getLog()).thenReturn(mockLogger);
        
        // Create instance using constructor
        JettyWebSocket jettyWebSocket = new JettyWebSocket(mockCtx);
        
        // Create a real Session mock that will be called in handleClose
        Session mockSession = Mockito.mock(Session.class);
        RemoteEndpoint mockRemoteEndpoint = Mockito.mock(RemoteEndpoint.class);
        Mockito.when(mockSession.getRemote()).thenReturn(mockRemoteEndpoint);
        Mockito.when(mockSession.isOpen()).thenReturn(true);
        
        // Set the session field via reflection since it's private
        java.lang.reflect.Field sessionField = JettyWebSocket.class.getDeclaredField("session");
        sessionField.setAccessible(true);
        sessionField.set(jettyWebSocket, mockSession);
        
        // Set open field to true via reflection
        java.lang.reflect.Field openField = JettyWebSocket.class.getDeclaredField("open");
        openField.setAccessible(true);
        AtomicBoolean openValue = (AtomicBoolean) openField.get(jettyWebSocket);
        openValue.set(true);
        
        // Create a WebSocketCloseStatus
        WebSocketCloseStatus closeStatus = WebSocketCloseStatus.NORMAL;
        
        // Call the entry point method
        jettyWebSocket.close(closeStatus);
    }
}
