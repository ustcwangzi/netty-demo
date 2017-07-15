package com.wz.netty.serialize.pojo;

import java.io.Serializable;

/**
 * Created by wangzi on 2017-07-15.
 */
public class BookReq implements Serializable {
    private static final long serialVersionUID = 9018439913872405356L;
    private int bookID;
    private String bookName;
    private String userName;
    private String address;

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "BookReq{" +
                "bookID=" + bookID +
                ", bookName='" + bookName + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
