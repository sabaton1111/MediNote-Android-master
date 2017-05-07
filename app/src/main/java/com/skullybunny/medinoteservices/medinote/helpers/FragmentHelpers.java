package com.skullybunny.medinoteservices.medinote.helpers;

import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Created by Martin on 4/26/2017.
 */

public interface FragmentHelpers {
    public  void changeFragment(Fragment fragment, boolean addReverseTransaction);

    public void changeFragment(Fragment fragment, boolean addReverseTransaction, int containerId);
}
