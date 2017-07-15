package com.wz.netty.serialize;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * Created by wangzi on 2017-07-15.
 */
public class NioClient {
    private static final int port = 8080;

    public void connect(int port, String host) throws Exception {
        // 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            //对POJO对象进行解码，cacheDisabled禁止对类加载器进行缓存，适用于动态模块化编程
                            //此处将单个对象最大序列化后的字节数组长度设置为1M
                            ch.pipeline().addLast(new ObjectDecoder(1024*1024,
                                    ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                            //发送消息时自动将实现了Serializable的POJO对象进行编码
                            ch.pipeline().addLast(new ObjectEncoder());
                            ch.pipeline().addLast(new NioClientHandler());
                        }
                    });
            // 发起异步连接操作
            ChannelFuture f = b.connect(host, port).sync();
            // 当代客户端链路关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new NioClient().connect(port, "127.0.0.1");
    }
}
