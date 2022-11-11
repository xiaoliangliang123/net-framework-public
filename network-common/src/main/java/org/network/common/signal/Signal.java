package org.network.common.signal;

import java.io.Serializable;

/**
 * @Author alan.wang
 * @description:  signal 信号顶层父类
 */
public class Signal implements Serializable {

    public static final Integer SIGNAL_MSG  = 1;
    public static final Integer SIGNAL_ACTION  = 2;

    public Signal(){}
    public Signal(int type,int subType){
       this.type = type;
       this.subType = subType;
    }

    protected int type ;
    protected int subType ;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isTypeOf(int type){
        return this.type == type;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }
}
