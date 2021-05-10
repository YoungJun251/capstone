package com.example.myapplication.DAO;

public class Notice {
    String text;
    String date;
    String cnt;

    public Notice(String text, String date, String cnt)
    {
        this.text = text;
        this.date = date;
        this.cnt = cnt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }
}
