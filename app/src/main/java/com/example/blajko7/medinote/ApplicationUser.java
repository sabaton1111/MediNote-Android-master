package com.example.blajko7.medinote;

/**
 * Created by WWW on 15-Mar-17.
 */

public class ApplicationUser {
    private static OAuthToken mAccessToken;

    public static void setAccessToken(OAuthToken token)
    {
        mAccessToken = token;
    }

    public static OAuthToken getAccessToken()
    {
        return mAccessToken;
    }

    public static String getAuthorization()
    {
        if (mAccessToken == null)
        {
            return null;
        }
        return mAccessToken.getAuthorization();
    }
}
