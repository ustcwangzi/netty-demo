package com.wz.nio;

/**
 * Created by wangzi on 2017/5/1.
 */
public class NioServer {
    private static final int port = 8080;

    public static void main(String[] args) {
        NioServerHandler handler = new NioServerHandler(port);
        new Thread(handler, "NioServer").start();
    }
}
