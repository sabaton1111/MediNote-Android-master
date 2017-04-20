package com.skullybunny.medinoteservices.medinote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WWW on 16-Mar-17.
 */

public class DoctorRegistrationDTO extends Doctor {

    @SerializedName("Password")
    private String password;

    @SerializedName("ConfirmPassword")
    private String confirmPassword;

    public DoctorRegistrationDTO(String name, String position, String uin, String healthcareFacilityName,
                                 String email, String phoneNumber, String nin, String password, String confirmPassword)
    {
        super(name, position, uin, healthcareFacilityName, email, phoneNumber, nin);
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public DoctorRegistrationDTO()
    {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
