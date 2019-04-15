package com.example.bruno.onlinecoach.Objects;

public class User {
    String email, password, name;
    int type;

    public User() {
    }

    public User(String email, String password, String name, int type) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.type = type;
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
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
