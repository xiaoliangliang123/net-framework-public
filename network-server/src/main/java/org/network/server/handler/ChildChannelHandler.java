package org.network.server.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.network.server.quene.SocketChannelInfo;
import org.network.server.quene.SocketChannelInfoQuene;

/**
 * @Author alan.wang
 * @description: 客户端连接到服务端时触发的回调，里面我将连接的客户端保存到了quene中
 */
public class ChildChannelHandler extends ChannelInitializer<NioSocketChannel> {

    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {

        String ip = nioSocketChannel.remoteAddress().getAddress().getHostAddress();
        if(SocketChannelInfoQuene.scIsQuene(ip)){
            nioSocketChannel.close();
            System.out.println("socket channel ip:"+ip+" is exist, closed");
        }else {
            SocketChannelInfo socketChannelInfo = SocketChannelInfo.build(ip,nioSocketChannel);
            SocketChannelInfoQuene.putScInQuene(ip,socketChannelInfo);
            nioSocketChannel.pipeline().addLast(new ServerChannelHandler());
            System.out.println("socket channel ip:"+ip+" connected ");

        }
    }
}
