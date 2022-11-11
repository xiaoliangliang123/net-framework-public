package org.network.server.handler.action;

import cn.hutool.json.JSONUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.network.common.signal.*;
import org.network.common.handler.AbstractActionSignalHandler;
import org.network.server.quene.SocketChannelInfo;
import org.network.server.quene.SocketChannelInfoQuene;

import java.util.List;

/**
 * @Author alan.wang
 * @description: 对 AbstractActionSignalHandler 的扩展，用来处理SetNameActionSignal 信号
 */
public class ActionSignalSetNameHandler extends AbstractActionSignalHandler {


    @Override
    public boolean isHandlerof(int type, int subType) {
        return type == Signal.SIGNAL_ACTION && subType == ActionSignal.ACTION_TYPE_SET_NAME;
    }



    @Override
    public void handle(String signalStr, ChannelHandlerContext ctx,String ip) {


        SetNameActionSignal setNameActionSignal = JSONUtil.toBean(signalStr, SetNameActionSignal.class);
        SocketChannelInfo socketChannelInfo = SocketChannelInfoQuene.getScFromQuene(ip);
        socketChannelInfo.setName(setNameActionSignal.getMsg());


        //从queue 中获取所有客户端连接并循环发送
        List<SocketChannelInfo> socketChannelInfos =  SocketChannelInfoQuene.quene();
        socketChannelInfos.forEach(scinfo->{
            PublicNormalMsgSignal publicNormalMsgSignal = new PublicNormalMsgSignal(MsgSignal.MSG_TYPE_MSG_PUBLIC,"["+setNameActionSignal.getMsg()+"]"+"上线");
            byte[] bytes = JSONUtil.toJsonPrettyStr(publicNormalMsgSignal).getBytes();
            ByteBuf byteBuf = Unpooled.buffer(bytes.length);
            byteBuf.writeBytes(bytes);
            scinfo.getNioSocketChannel().writeAndFlush(byteBuf);
        });
    }
}
