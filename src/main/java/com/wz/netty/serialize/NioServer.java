package com.wz.netty.serialize;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * Created by wangzi on 2017-07-15.
 */
public class NioServer {
    private static final int port = 8080;

    public void bind(int port) throws Exception {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());
            // 绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            //对POJO对象进行解码，weakCachingConcurrentResolver创建线程安全的WeakReferenceMap对类加载器进行缓存，
            //支持多线程并发访问，当JVM内存不足时，会释放缓存中的内存，防止内存泄漏
            //此处将单个对象最大序列化后的字节数组长度设置为1M
            ch.pipeline().addLast(new ObjectDecoder(1024*1024,
                    ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
            //发送消息时自动将实现了Serializable的POJO对象进行编码
            ch.pipeline().addLast(new ObjectEncoder());
            ch.pipeline().addLast(new NioServerHandler());
        }
    }

    public static void main(String[] args) throws Exception{
        new NioServer().bind(port);
    }
}
