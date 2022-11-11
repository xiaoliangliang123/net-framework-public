package org.network.client.dispatcher;

import org.network.client.handler.msg.MsgSignalPublicNormalHandler;
import org.network.common.dispatcher.AbstractSignalHandlerDispatcher;
import org.network.common.handler.AbstractSignalHandler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author alan.wang
 * @description: 对 AbstractSignalHandlerDispatcher 的扩展，只需要stream 里面不断添加自定义handler即可
 */
public class ClientSignalHandlerDispatcher extends AbstractSignalHandlerDispatcher {

    //在这里添加自己的客户端业务处理handler即可
    private static List<AbstractSignalHandler> signalHandlers = Stream.of(new MsgSignalPublicNormalHandler()).collect(Collectors.toList());


    @Override
    public List<AbstractSignalHandler> getSignalHandlers() {
        return signalHandlers;
    }
}
