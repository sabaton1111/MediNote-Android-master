package com.skullybunny.medinoteservices.medinote;

/**
 * Created by WWW on 15-Mar-17.
 */

public class ApplicationUser {
    private static OAuthToken mToken;
    private String accountType;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public ApplicationUser()
    {

    }

    public ApplicationUser(OAuthToken token)
    {
        mToken = token;
    }

    public void setToken(OAuthToken token)
    {
        mToken = token;
    }

    public OAuthToken getToken()
    {
        return mToken;
    }

    public String getAuthorization()
    {
        if (mToken == null)
        {
            return null;
        }
        return mToken.getAuthorization();
    }
}
