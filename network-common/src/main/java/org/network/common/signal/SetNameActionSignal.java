package org.network.common.signal;

import java.io.Serializable;

/**
 * @Author alan.wang
 * @description:  SetNameActionSignal 修改名称动作信号类
 */
public class SetNameActionSignal extends ActionSignal  {

    public SetNameActionSignal(){
        super();
    }
    public SetNameActionSignal(Integer actionType, String msg) {
        super(actionType, msg);
    }
}
