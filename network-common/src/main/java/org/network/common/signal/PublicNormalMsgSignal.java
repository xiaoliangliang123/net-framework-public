package org.network.common.signal;

/**
 * @Author alan.wang
 * @description:  PublicNormalMsgSignal 普通公开消息信号类
 */
public class PublicNormalMsgSignal extends MsgSignal {

    public PublicNormalMsgSignal(){
        super();
    }
    public PublicNormalMsgSignal(Integer msgType, String msg) {
        super(msgType, msg);
    }
}
