package com.skullybunny.medinoteservices.medinote;

import com.google.gson.annotations.SerializedName;

public class OAuthToken {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    public OAuthToken(String accessToken, String tokenType)
    {
        setAccessToken(accessToken);
        setTokenType(tokenType);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getAuthorization() {
        return getTokenType() + " " + getAccessToken();
    }

    public void setAccessToken(String token) {
        this.accessToken = token;
    }

    public void setTokenType(String type) {
        this.tokenType = type;
    }
}