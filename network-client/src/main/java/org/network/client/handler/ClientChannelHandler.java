package org.network.client.handler;

import cn.hutool.json.JSONUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.network.client.dispatcher.ClientSignalHandlerDispatcher;
import org.network.common.handler.AbstractSignalHandler;
import org.network.common.signal.ActionSignal;
import org.network.common.signal.SetNameActionSignal;
import org.network.common.signal.Signal;

import java.util.Objects;

/**
 * @Author alan.wang
 * @description: 监听服务端发送来的信息，一般情况下无需改动直接拷贝使用即可
 */
public class ClientChannelHandler extends ChannelInboundHandlerAdapter {

    private ClientSignalHandlerDispatcher clientSignalHandlerDispatcher = new ClientSignalHandlerDispatcher();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        SetNameActionSignal setNameActionSignal = new SetNameActionSignal(ActionSignal.ACTION_TYPE_SET_NAME,"alan");
        byte[] bytes = JSONUtil.toJsonPrettyStr(setNameActionSignal).getBytes();
        ByteBuf byteBuf = Unpooled.buffer(bytes.length);
        byteBuf.writeBytes(bytes);
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String signalStr = new String(bytes);
        Signal signal = JSONUtil.toBean(signalStr, Signal.class);
        AbstractSignalHandler abstractSignalHandler = clientSignalHandlerDispatcher.dispatch(signal);
        String ip = ((NioSocketChannel)ctx.channel()).remoteAddress().getAddress().getHostAddress();
        if(Objects.nonNull(abstractSignalHandler)) {
            abstractSignalHandler.handle(signalStr, ctx, ip);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
