package org.network.server.quene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author alan.wang
 * @description: 保存了所有客户端连接channel 连接的quene,可以用来获取所有客户端连接，应加上lock 操作，后续会添加
 */
public class SocketChannelInfoQuene {

    private static Map<String, SocketChannelInfo> scMap = new HashMap<>();

    public static void putScInQuene(String ip,SocketChannelInfo socketChannelInfo){
        scMap.put(ip,socketChannelInfo);
    }

    public static boolean scIsQuene(String ip){
        return scMap.containsKey(ip);
    }
    public static void removeScFromQuene(String ip){
        scMap.remove(ip);
    }

    public static SocketChannelInfo getScFromQuene(String ip){
        return scMap.get(ip);
    }


    public static List<SocketChannelInfo> quene() {
        return new ArrayList<>(scMap.values());
    }
}
