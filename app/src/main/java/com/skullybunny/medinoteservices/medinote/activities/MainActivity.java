package com.skullybunny.medinoteservices.medinote.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.skullybunny.medinoteservices.medinote.authenticator.CurrentUser;
import com.skullybunny.medinoteservices.medinote.fragments.LoginFragment;
import com.skullybunny.medinoteservices.medinote.R;

public class MainActivity extends BaseActivity {

    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeCurrentUser();
    }

    private void initializeCurrentUser()
    {
        if (!CurrentUser.initialize(this))
        {
            changeFragment(new LoginFragment(), false);
        }
        else
        {
            Intent mainNavActivity = new Intent(this, Navigation.class);
            startActivity(mainNavActivity);
            finish();
        }
    }
}
