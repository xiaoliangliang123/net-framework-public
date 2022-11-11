import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.network.common.util.ArgsUtil;
import org.network.server.handler.ChildChannelHandler;

/**
 * @Author alan.wang
 * @description: 服务端启动类，仅供参考，可以自己扩充
 */
public class Server {

    public static void main(String[] args) throws InterruptedException {

        //设置主进程线程
        EventLoopGroup mainGroup = new NioEventLoopGroup();

        //设置工作线程
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            //获取启动参数并设置，目前只有port ,比如 --port 8080 等
            ArgsUtil.RunArgs runArgs = ArgsUtil.init(args);
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            //设置channel 类型为NioServerSocketChannel 并设置操作参数
            serverBootstrap.group(mainGroup, workGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChildChannelHandler());
            ChannelFuture channelFuture;
            if (runArgs.hasPort()) {

                //设置启动端口，端口从启动参数获取
                channelFuture = serverBootstrap.bind(runArgs.getPort()).sync();
            } else {

                //设置启动端口，外部没有设定则写死9898
                channelFuture = serverBootstrap.bind(9898).sync();
            }

             //设置阻塞主进程
             channelFuture.channel().closeFuture().sync();
        }finally {

            //优雅退出并关闭主进程线程
            mainGroup.shutdownGracefully();

            //
            workGroup.shutdownGracefully();
        }
    }
}
