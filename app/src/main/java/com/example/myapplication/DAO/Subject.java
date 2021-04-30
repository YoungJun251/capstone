package com.example.myapplication.DAO;

public class Subject {
    String name;
    String date;
    String professor;

    public Subject(String name ,String date, String professor)
    {
        this.name = name;
        this.date = date;
        this.professor = professor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
