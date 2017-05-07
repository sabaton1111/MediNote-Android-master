package com.skullybunny.medinoteservices.medinote.authenticator;

import android.content.Context;
import android.content.SharedPreferences;

import com.skullybunny.medinoteservices.medinote.R;
import com.skullybunny.medinoteservices.medinote.models.OAuthToken;

/**
 * Created by WWW on 15-Mar-17.
 */

public class CurrentUser {
    private static ApplicationUser mApplicationUser;

    public static void setUser(Context context, ApplicationUser appUser)
    {
        mApplicationUser = appUser;
        saveCredentials(context);
    }

    public static ApplicationUser getUser()
    {
        return mApplicationUser;
    }

    public static boolean initialize(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.token_file_name), Context.MODE_PRIVATE);
        String tokenType = sharedPreferences.getString(context.getString(R.string.token_type), null);
        String accessToken = sharedPreferences.getString(context.getString(R.string.access_token), null);
        String accountType = sharedPreferences.getString("accountType", null);

        if (accessToken == null || tokenType == null)
        {
            return false;
        }

        ApplicationUser appUser = new ApplicationUser();
        OAuthToken token = new OAuthToken(accessToken, tokenType);
        appUser.setToken(token);
        appUser.setAccountType(accountType);
        setUser(context, appUser);
        return true;
    }

    public static void logOut(Context context)
    {
        SharedPreferences.Editor sharedPreferencesEditor = context.getSharedPreferences(context.getString(R.string.token_file_name), Context.MODE_PRIVATE).edit();

        sharedPreferencesEditor.remove(context.getString(R.string.access_token));
        sharedPreferencesEditor.remove(context.getString(R.string.token_type));
        sharedPreferencesEditor.remove("accountType");

        sharedPreferencesEditor.apply();

        mApplicationUser = null;
    }

    private static void saveCredentials(Context context)
    {
        SharedPreferences.Editor sharedPreferencesEditor = context.getSharedPreferences(
                context.getString(R.string.token_file_name), Context.MODE_PRIVATE).edit();

        OAuthToken token = getUser().getToken();
        String accountType = getUser().getAccountType();

        sharedPreferencesEditor.putString(context.getString(R.string.access_token), token.getAccessToken());
        sharedPreferencesEditor.putString(context.getString(R.string.token_type), token.getTokenType());
        sharedPreferencesEditor.putString("accountType", accountType);

        sharedPreferencesEditor.apply();
    }
}
