package org.network.client.handler.msg;

import cn.hutool.json.JSONUtil;
import io.netty.channel.ChannelHandlerContext;
import org.network.common.handler.AbstractActionSignalHandler;
import org.network.common.handler.AbstractMsgSignalHandler;
import org.network.common.signal.*;

/**
 * @Author alan.wang
 * @description: 对 AbstractActionSignalHandler 的扩展，用来处理PublicNormalMsgSignal 信号
 */
public class MsgSignalPublicNormalHandler extends AbstractMsgSignalHandler {

    @Override
    public boolean isHandlerof(int type, int subType) {
        return type == Signal.SIGNAL_MSG && subType == MsgSignal.MSG_TYPE_MSG_PUBLIC;
    }

    @Override
    public void handle(String signalStr, ChannelHandlerContext ctx, String ip) {
        PublicNormalMsgSignal publicNormalMsgSignal = JSONUtil.toBean(signalStr, PublicNormalMsgSignal.class);
        System.out.println("接到公共消息："+publicNormalMsgSignal.getMsg());
    }
}
