package com.wz.netty.serialize;

import com.wz.netty.serialize.pojo.BookReq;
import com.wz.netty.serialize.pojo.BookResp;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by wangzi on 2017-07-15.
 */
public class NioClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i=0; i<10; i++){
            ctx.write(req(i));
        }
        ctx.flush();
    }

    private BookReq req(int i){
        BookReq req = new BookReq();
        req.setBookID(i+1);
        req.setBookName("Netty");
        req.setUserName("WangZi");
        req.setAddress("USTC");
        return req;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        BookResp resp = (BookResp) msg;
        System.out.println("Receive : " + resp.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
