package com.skullybunny.medinoteservices.medinote.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.skullybunny.medinoteservices.medinote.helpers.FragmentHelpers;
import com.skullybunny.medinoteservices.medinote.R;

/**
 * Created by Martin on 4/24/2017.
 */

public class BaseFragment extends Fragment implements FragmentHelpers {

    protected Activity mActivity;

    protected void initializeViews(View view)
    {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

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
