package org.network.common.handler;

import io.netty.channel.ChannelHandlerContext;
import org.network.common.signal.Signal;

/**
 * @Author alan.wang
 * @description: 对ActionSignal 动作信息的处理抽象类
 */
public abstract class AbstractSignalHandler {

    public abstract boolean isHandlerof(int type,int actionType);


    public abstract void handle(String signalStr, ChannelHandlerContext ctx,String ip);
}
