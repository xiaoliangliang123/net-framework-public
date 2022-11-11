package org.network.common.signal;

import java.io.Serializable;

/**
 * @Author alan.wang
 * @description:  MsgSignal 消息信号父类

 */
public class MsgSignal extends Signal  {

    public static final Integer MSG_TYPE_MSG_PUBLIC = 3;

    private String msg;

    public MsgSignal(){}
    public MsgSignal(Integer actionType ,String msg){
        super(Signal.SIGNAL_MSG,actionType);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
