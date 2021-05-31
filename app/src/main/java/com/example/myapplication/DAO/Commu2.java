package com.example.myapplication.DAO;

public class Commu2 {
    String cnt;
    String date;
    String num;


    public Commu2(String cnt, String d, String c)
    {
        this.cnt = cnt;
        this.date = d;
        this.num = c;
    }
    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

 }

