package com.example.library_mgmt.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
    private int rollno;
    private String name;
    private String password;

    public  Student(){
        this.rollno = -1;
        this.name = "";
        this.password = "";
    }

    public Student(int rollno, String password) {
        this.rollno = rollno;
        this.name = "";
        this.password = password;
    }

    public Student(int rollno, String name, String password) {
        this.rollno = rollno;
        this.name = name;
        this.password = password;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.length() > 6)
            this.password = password;
    }
}
