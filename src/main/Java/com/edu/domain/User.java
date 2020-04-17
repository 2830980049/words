package com.edu.domain;

import java.util.Date;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String real_name;
    private Date birthday;
    private String phone;
    private String address;

    public User() {
    }

    public User(Integer id, String username, String password, String real_name, Date birthday, String phone, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.real_name = real_name;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", real_name='" + real_name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
}
