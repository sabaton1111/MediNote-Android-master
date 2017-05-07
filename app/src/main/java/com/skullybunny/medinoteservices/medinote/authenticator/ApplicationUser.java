package com.skullybunny.medinoteservices.medinote.authenticator;

import com.skullybunny.medinoteservices.medinote.models.OAuthToken;

/**
 * Created by WWW on 15-Mar-17.
 */

public class ApplicationUser {
    private static OAuthToken mToken;
    private String mAccountType;

    public String getAccountType() {
        return mAccountType;
    }

    public void setAccountType(String accountType) {
        this.mAccountType = accountType;
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
