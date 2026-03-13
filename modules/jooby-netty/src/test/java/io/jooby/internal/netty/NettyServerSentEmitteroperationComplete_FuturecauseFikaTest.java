package io.jooby.internal.netty;

import io.jooby.SneakyThrows;
import io.netty.util.concurrent.Future;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class NettyServerSentEmitteroperationComplete_FuturecauseFikaTest {

    @Test
    public void testOperationCompleteInvokesCause() throws Exception {
        NettyContext nettyContext = Mockito.mock(NettyContext.class);
        NettyServerSentEmitter emitter = new NettyServerSentEmitter(nettyContext);
        
        Future future = Mockito.mock(Future.class);
        when(future.isSuccess()).thenReturn(false);
        when(future.cause()).thenReturn(new Exception("test"));
        
        emitter.operationComplete(future);
    }
}
