package com.example.library_mgmt.models;

public class Admin {
    private int id;
    private String name;
    private String email;
    private String password;

    public Admin()
    {
        this.name = "";
        this.email = "";
        this.password = "";
    }

    public Admin(String email, String password){
        this.name = "";
        this.email = email;
        this.password = password;
    }

    public Admin(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.length() > 6)
            this.password = password;
    }
}
