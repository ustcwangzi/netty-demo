package com.wz.aio;

/**
 * Created by wangzi on 2017/5/1.
 */
public class AioServer {
    private static final int port = 8080;

    public static void main(String[] args) {
        AsyncServerHandler handler = new AsyncServerHandler(port);
        new Thread(handler, "AsyncServerHandler").start();
    }
}
