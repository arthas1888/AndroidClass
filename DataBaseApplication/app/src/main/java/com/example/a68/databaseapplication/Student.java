package com.example.a68.databaseapplication;


import java.io.Serializable;

public class Student implements Serializable {

    private String name;
    private String phone;
    private String career;

    public Student() {
    }

    public Student(String name, String phone, String career) {
        this.name = name;
        this.phone = phone;
        this.career = career;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
}
