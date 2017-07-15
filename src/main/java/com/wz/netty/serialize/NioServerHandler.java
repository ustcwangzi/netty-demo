package com.wz.netty.serialize;

import com.wz.netty.serialize.pojo.BookReq;
import com.wz.netty.serialize.pojo.BookResp;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by wangzi on 2017-07-15.
 */
public class NioServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        BookReq req = (BookReq) msg;
        System.out.println("Receive : " + req.toString());
        ctx.writeAndFlush(resp(req.getBookID()));
    }

    private BookResp resp(int bookID){
        BookResp resp = new BookResp();
        resp.setBookID(bookID);
        resp.setDesc("The book received succeed!");
        return resp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
