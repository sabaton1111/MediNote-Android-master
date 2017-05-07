package com.skullybunny.medinoteservices.medinote.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WWW on 16-Mar-17.
 */

public class Student {

    @SerializedName("studentName")
    private String name;

    @SerializedName("studentNIN")
    private String nin;

    @SerializedName("studentAddress")
    private String address;

    @SerializedName("studentAge")
    private int age;

    public Student()
    {

    }

    public Student(String name, int age, String address, String nin)
    {
        this.name = name;
        this.age = age;
        this.nin = nin;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
