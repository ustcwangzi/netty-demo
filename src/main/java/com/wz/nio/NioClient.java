package com.wz.nio;

/**
 * Created by wangzi on 2017/5/1.
 */
public class NioClient {
    private static final int port = 8080;

    public static void main(String[] args) {
        new Thread(new NioClientHandler("127.0.0.1", port), "NioClient").start();
    }
}
