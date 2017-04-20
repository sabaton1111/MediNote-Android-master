package com.skullybunny.medinoteservices.medinote;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @POST("api/students")
    Call<Void> registerStudent(@Body Student student, @Header("Authorization") String token);

    @POST("api/doctors")
    Call<Void> registerDoctor(@Body DoctorRegistrationDTO doctor, @Header("Authorization") String token);

    @GET("api/medicalnotes/{men}")
    Call<MedicalNote> getMedicalNoteByMEN(@Path("men") String men, @Header("Authorization") String token);
}
