package org.network.common.signal;

import java.io.Serializable;

/**
 * @Author alan.wang
 * @description:  actionSignal 动作信号父类
 */
public class ActionSignal extends Signal  {

    public static final Integer ACTION_TYPE_SET_NAME = 1;


    private String msg;

    public ActionSignal(){};
    public ActionSignal(Integer actionType ,String msg){
        super(Signal.SIGNAL_ACTION,actionType);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



}
