package org.network.common.dispatcher;

import org.network.common.handler.AbstractSignalHandler;
import org.network.common.signal.Signal;

import java.util.List;

/**
 * @Author alan.wang
 * @description: 对signal 信号类型派发处理handler 的抽象类
 */
public abstract class AbstractSignalHandlerDispatcher {

    public abstract  List<AbstractSignalHandler> getSignalHandlers();

    public  AbstractSignalHandler dispatch(Signal signal) {

        //判断是否是某个自定义handler 处理的方法，如果是则返回
        return getSignalHandlers().stream().filter(handler->handler.isHandlerof(signal.getType(),signal.getSubType())).findFirst().get();
    }
}
