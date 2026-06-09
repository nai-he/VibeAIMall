package com.asyyy.shixun;

public class User {
    private int user_id;
    private String username;
    private String password;
    private String phone;
    private String address;
    private String name;

    public User() {
    }

    public User(int user_id, String username, String password, String phone, String address, String name) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.name = name;
    }

    // Getter和Setter方法
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}