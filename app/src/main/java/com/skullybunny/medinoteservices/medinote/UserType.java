package com.skullybunny.medinoteservices.medinote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Martin on 4/22/2017.
 */

public class UserType
{
    @SerializedName("AccountType")
    private String accountType;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
