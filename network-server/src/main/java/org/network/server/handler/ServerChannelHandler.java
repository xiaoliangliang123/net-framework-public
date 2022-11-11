package org.network.server.handler;

import cn.hutool.json.JSONUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.network.common.handler.AbstractSignalHandler;
import org.network.common.signal.Signal;
import org.network.server.dispatcher.ServerSignalHandlerDispatcher;
import org.network.server.quene.SocketChannelInfoQuene;

import java.util.Objects;

/**
 * @Author alan.wang
 * @description: 监听客户端发送来的信息，一般情况下无需改动直接拷贝使用即可
 */
public class ServerChannelHandler extends ChannelInboundHandlerAdapter {

    private ServerSignalHandlerDispatcher signalHandlerDispatcher  = new ServerSignalHandlerDispatcher();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String signalStr = new String(bytes);
        Signal signal = JSONUtil.toBean(signalStr, Signal.class);

        AbstractSignalHandler abstractSignalHandler = signalHandlerDispatcher.dispatch(signal);
        String ip = ((NioSocketChannel)ctx.channel()).remoteAddress().getAddress().getHostAddress();
        if(Objects.nonNull(abstractSignalHandler)) {
            abstractSignalHandler.handle(signalStr, ctx, ip);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        String ip = ((NioSocketChannel)ctx.channel()).remoteAddress().getAddress().getHostAddress();
        System.out.println("channel disconnect ,ip:"+ip);
        SocketChannelInfoQuene.removeScFromQuene(ip);
        System.err.println(cause.getMessage());
        ctx.close();
    }
}
