package io.jooby.internal.netty;

import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtensionHandler;
import io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtensionHandshaker;
import io.netty.handler.codec.http.websocketx.extensions.compression.DeflateFrameServerExtensionHandshaker;
import io.netty.handler.codec.http.websocketx.extensions.compression.PerMessageDeflateServerExtensionHandshaker;
import org.junit.jupiter.api.Test;

public class NettyWebSocketCompressormethod_PerMessageDeflateServerExtensionHandshakermethodFikaTest {

    @Test
    public void testEntryPointToThirdPartyMethod() {
        int compressionLevel = 6;
        NettyWebSocketCompressor instance = new NettyWebSocketCompressor(compressionLevel);
    }
}
