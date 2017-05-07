package com.skullybunny.medinoteservices.medinote;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Martin on 4/24/2017.
 */

public class BaseFragment extends Fragment {

    protected Activity mActivity;

    protected void initializeViews(View view)
    {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    protected  void changeFragment(Fragment fragment, boolean addReverseTransaction)
    {
        changeFragment(fragment, addReverseTransaction, R.id.container);
    }

    protected void changeFragment(Fragment fragment, boolean addReverseTransaction, int containerId)
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
