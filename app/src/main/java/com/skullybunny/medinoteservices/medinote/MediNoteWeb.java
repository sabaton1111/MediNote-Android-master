package com.skullybunny.medinoteservices.medinote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WWW on 17-Mar-17.
 */

public class MediNoteWeb {

    private static MediNoteWebAPI mMediNoteWebAPI;

    public static MediNoteWebAPI getWebAPIInstance()
    {
        if (mMediNoteWebAPI == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MediNoteWebAPI.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mMediNoteWebAPI = retrofit.create(MediNoteWebAPI.class);
        }

        return mMediNoteWebAPI;
    }
}
