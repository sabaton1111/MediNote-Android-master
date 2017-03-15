package com.example.blajko7.medinote;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by WWW on 14-Mar-17.
 */

public interface MediNoteWebAPI {
    String BASE_URL = "https://medinote.azurewebsites.net/";

    @FormUrlEncoded
    @POST("api/token")
    Call<OAuthToken> postCredentials(@Field("grant_type") String grantType,
                                     @Field("username") String username,
                                     @Field("password") String password);
}
