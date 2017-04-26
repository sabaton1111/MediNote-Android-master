package com.skullybunny.medinoteservices.medinote;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends Activity {

    SharedPreferences mSharedPreferences;
    private void ChangeFragment(Fragment fragment, boolean addReverseTransaction)
    {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
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

        initializeCurrentUser();
    }

    private void initializeCurrentUser()
    {
        if (!CurrentUser.initialize(this))
        {
            ChangeFragment(new LoginFragment(), false);
        }
        else
        {
            Intent mainNavActivity = new Intent(this, Navigation.class);
            startActivity(mainNavActivity);
            finish();
        }
    }
}
