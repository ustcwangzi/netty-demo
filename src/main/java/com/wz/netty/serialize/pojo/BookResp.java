package com.wz.netty.serialize.pojo;

import java.io.Serializable;

/**
 * Created by wangzi on 2017-07-15.
 */
public class BookResp implements Serializable {
    private static final long serialVersionUID = 79675058524240191L;
    private int bookID;
    private String desc;

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BookResp{" +
                "bookID=" + bookID +
                ", desc='" + desc + '\'' +
                '}';
    }
}
