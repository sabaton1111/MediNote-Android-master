package com.skullybunny.medinoteservices.medinote.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.skullybunny.medinoteservices.medinote.helpers.FragmentHelpers;
import com.skullybunny.medinoteservices.medinote.R;

/**
 * Created by Martin on 4/26/2017.
 */

public class BaseActivity extends AppCompatActivity implements FragmentHelpers {

    @Override
    public  void changeFragment(Fragment fragment, boolean addReverseTransaction)
    {
        changeFragment(fragment, addReverseTransaction, R.id.container);
    }

    @Override
    public void changeFragment(Fragment fragment, boolean addReverseTransaction, int containerId)
    {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId, fragment);

        if (addReverseTransaction)
        {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }
}
