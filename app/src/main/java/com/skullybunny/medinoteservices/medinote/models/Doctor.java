package com.skullybunny.medinoteservices.medinote.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WWW on 16-Mar-17.
 */

public class Doctor {

    @SerializedName("DoctorName")
    private String name;

    @SerializedName("DoctorPosition")
    private String position;

    @SerializedName("DoctorUIN")
    private String uin;

    @SerializedName("HealthcareFacilityName")
    private String healthcareFacilityName;

    @SerializedName("DoctorEmail")
    private String email;

    @SerializedName("DoctorPhoneNumber")
    private String phoneNumber;

    @SerializedName("DoctorNIN")
    private String nin;

    public Doctor(String name, String position, String uin, String healthcareFacilityName,
                  String email, String phoneNumber, String nin)
    {
        this.name = name;
        this.position = position;
        this.uin = uin;
        this.healthcareFacilityName = healthcareFacilityName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nin = nin;
    }

    public Doctor()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public String getHealthcareFacilityName() {
        return healthcareFacilityName;
    }

    public void setHealthcareFacilityName(String healthcareFacilityName) {
        this.healthcareFacilityName = healthcareFacilityName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }
}
