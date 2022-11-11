package org.network.server.quene;

import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author alan.wang
 * @description: 保存了所有客户端连接channel 的ip和名称信息,可以自定义扩展
 */
public class SocketChannelInfo {

    private String name ;

    private String ip;

    private NioSocketChannel nioSocketChannel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public NioSocketChannel getNioSocketChannel() {
        return nioSocketChannel;
    }

    public void setNioSocketChannel(NioSocketChannel nioSocketChannel) {
        this.nioSocketChannel = nioSocketChannel;
    }

    public static SocketChannelInfo build(String ip ,NioSocketChannel nioSocketChannel){
        SocketChannelInfo socketChannelInfo = new SocketChannelInfo();
        socketChannelInfo.setIp(ip);
        socketChannelInfo.setNioSocketChannel(nioSocketChannel);
        return socketChannelInfo;
    }
}
