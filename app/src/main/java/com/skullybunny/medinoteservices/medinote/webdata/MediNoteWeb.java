package com.skullybunny.medinoteservices.medinote.webdata;

import android.app.Activity;
import android.app.Application;

import java.lang.reflect.Type;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WWW on 17-Mar-17.
 */

public class MediNoteWeb {

    private static MediNoteWebAPI mMediNoteWebAPI;
    private static Retrofit mRetrofit;

    private static void initializeWebAPI()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MediNoteWebAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mMediNoteWebAPI = retrofit.create(MediNoteWebAPI.class);
        mRetrofit = retrofit;
    }

    public static MediNoteWebAPI getWebAPIInstance()
    {
        if (mMediNoteWebAPI == null) {
            initializeWebAPI();
        }

        return mMediNoteWebAPI;
    }

    public static <T> Converter<ResponseBody, T> getResponseConverter(Type type, Annotation[] annotations)
    {
        if (mMediNoteWebAPI == null) {
            initializeWebAPI();
        }

        return mRetrofit.responseBodyConverter(type, annotations);
    }
}
