package com.example.myapplication.DAO;

public class attend {
    String num;
    String name;
    String chk;
    String attend;

    public attend(String num,String name, String chk)
    {
        this.num = "    "+num+"    ";
        this.name = name;
        this.chk = chk;
    }


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChk() {
        return chk;
    }

    public void setChk(String chk) {
        this.chk = chk;
    }
}
