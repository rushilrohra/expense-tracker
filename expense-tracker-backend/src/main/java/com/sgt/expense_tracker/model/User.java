package com.sgt.expense_tracker.model;

public class User {
    int id;
    String name;
    String username;
    String email;
    String password;
    String mobile;
    int active_yn;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getActive_yn() {
        return active_yn;
    }

    public void setActive_yn(int active_yn) {
        this.active_yn = active_yn;
    }
}
