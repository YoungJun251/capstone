package com.example.myapplication.DAO;

public class Commu {
    String cnt;
    String date;
    String chk;

    public Commu(String cnt,String d, String c)
    {
        this.cnt = cnt;
        this.date = d;
        this.chk = c;
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

    public String getChk() {
        return chk;
    }

    public void setChk(String chk) {
        this.chk = chk;
    }
}
