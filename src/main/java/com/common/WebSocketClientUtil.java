package com.common;

import org.asynchttpclient.*;
import org.asynchttpclient.proxy.ProxyServer;
import org.asynchttpclient.ws.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by frank_zhao on 2017/8/24.
 *
 * @apiNote webClientSocket
 */
public class WebSocketClientUtil {

    private WebSocket websocket;
    private AsyncHttpClientConfig config;
    private AsyncHttpClient asyncHttpClient;

    /**
     * @param host
     * @param port
     */
    public WebSocketClientUtil(String host, int port) {
        config = new DefaultAsyncHttpClientConfig.Builder()
                .setProxyServer(new ProxyServer.Builder(host, port)).build();
        asyncHttpClient = new DefaultAsyncHttpClient(config);
    }


    /**
     * @param url
     * @param webSocketListener back handler
     * @throws ExecutionException
     * @throws InterruptedException
     * @apiNote open webSocket stream
     */
    public void connection(String url, WebSocketListener webSocketListener) throws ExecutionException,
            InterruptedException {
        websocket = asyncHttpClient.prepareGet(url)
                .execute(new WebSocketUpgradeHandler.Builder().addWebSocketListener(webSocketListener).build()).get();
    }

    /**
     * @throws IOException
     * @apiNote close stream
     */
    public void closeWebSocket() throws IOException {
        if (websocket != null) {
            websocket.close();
        }
    }
}
