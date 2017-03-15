package com.example.blajko7.medinote;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SharedPreferences mSharedPreferences;
    private void ChangeFragment(Fragment fragment, boolean addReverseTransaction)
    {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_home, fragment);
        if (addReverseTransaction)
        {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeSharedPreferences();
    }

    private void initializeSharedPreferences()
    {
        mSharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String tokenType = mSharedPreferences.getString(getString(R.string.token_type), null);
        String accessToken = mSharedPreferences.getString(getString(R.string.access_token), null);
        if (tokenType == null || accessToken == null)
        {
            ChangeFragment(new Login_fragment(), false);
        }
        else
        {
            ApplicationUser.setAccessToken(new OAuthToken(accessToken, tokenType));
            ChangeFragment(new Main_fragment(), false);
        }
    }
}
