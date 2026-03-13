package io.jooby.internal.netty;

import io.jooby.Server;
import io.jooby.SneakyThrows;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyServerSentEmitteroperationComplete_FutureisSuccessFikaTest {

    @Test
    public void testOperationCompleteInvokesFutureIsSuccess() throws Exception {
        NettyContext nettyContext = Mockito.mock(NettyContext.class);
        NettyServerSentEmitter emitter = new NettyServerSentEmitter(nettyContext);
        
        Future future = Mockito.mock(Future.class);
        when(future.isSuccess()).thenReturn(false);
        when(future.cause()).thenReturn(new Exception("test"));
        
        Mockito.mockStatic(Server.class);
        when(Server.connectionLost(Mockito.any(Throwable.class))).thenReturn(false);
        
        emitter.operationComplete(future);
    }
}
