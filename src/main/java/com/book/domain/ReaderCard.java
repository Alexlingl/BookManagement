package com.book.domain;

import java.io.Serializable;

public class ReaderCard implements Serializable{

    private int readerId;
    private String name;
    private String passwd;
    private int vipState;
    private int cardState;

    public void setVipState(int vipState){this.vipState = vipState;}

    public void setCardState(int cardState) {
        this.cardState = cardState;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getReaderId() {
        return readerId;
    }

    public int getCardState() {
        return cardState;
    }

    public int getVipState() { return vipState; }

    public String getName() {
        return name;
    }

    public String getPasswd() {
        return passwd;
    }
}
