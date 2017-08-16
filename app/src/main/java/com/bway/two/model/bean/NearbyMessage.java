package com.bway.two.model.bean;

/**
 * autor: 李金涛.
 * date:2017/8/14
 */


public class NearbyMessage {
    private String name;
    private double money;
    private int jifen;
    private int juli;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getJifen() {
        return jifen;
    }

    public void setJifen(int jifen) {
        this.jifen = jifen;
    }

    public int getJuli() {
        return juli;
    }

    public void setJuli(int juli) {
        this.juli = juli;
    }

    public NearbyMessage(String name, double money, int jifen, int juli) {
        this.name = name;
        this.money = money;
        this.jifen = jifen;
        this.juli = juli;
    }
}
