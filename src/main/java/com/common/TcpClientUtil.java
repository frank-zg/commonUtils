package com.common;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by frank_zhao on 2017/8/24.
 *
 * @apiNote TcpUtil
 */
public class TcpClientUtil {

    private String host;
    private int port;

    private Bootstrap bootstrap = new Bootstrap();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();


    public TcpClientUtil(String host, int port) {
        this.host = host;
        this.port = port;
    }


    /**
     * @param handler 消息接收回调,实现channelRead0方法, ChannelHandlerContext中可以拿到channel往server发送消息,String为接收到的消息
     * @return Channel 可以用channel持续给server发送消息,如要hold住连接请调用 channel.closeFuture().sync();
     * @throws InterruptedException
     * @apiNote 发起tcp连接
     */
    public ChannelFuture tcpConnection(SimpleChannelInboundHandler<String> handler) throws InterruptedException {
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new StringEncoder());
                ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(handler);
            }
        });
        return bootstrap.connect(host, port).sync();
    }


    public void tcpClose() {
        workerGroup.shutdownGracefully();
    }
}
