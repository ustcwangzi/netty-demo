package com.wz.aio;

/**
 * Created by wangzi on 2017/5/1.
 */
public class AioClient {
    private static final int port = 8080;

    public static void main(String[] args) {
        new Thread(new AsyncClientHandler("127.0.0.1", port), "AsyncClientHandler").start();
    }
}
