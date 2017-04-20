package com.skullybunny.medinoteservices.medinote;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

public class Main_fragment extends Fragment {
    private Activity mActivity;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        //Toast.makeText(mActivity, ApplicationUser.getToken().getAuthorization(), Toast.LENGTH_SHORT).show();

        TabHost th = (TabHost)view.findViewById(R.id.tabHost);
        th.setup();
        TabHost.TabSpec latest_tab = th.newTabSpec("Note");
        latest_tab.setContent(R.id.tab1);
        latest_tab.setIndicator("Note");
        th.addTab(latest_tab);
        latest_tab = th.newTabSpec("Certificate");
        latest_tab.setContent(R.id.tab2);
        latest_tab.setIndicator("Certificate");
        th.addTab(latest_tab);
        latest_tab = th.newTabSpec("MedicalCheck");
        latest_tab.setContent(R.id.tab3);
        latest_tab.setIndicator("MedicalCheck");
        th.addTab(latest_tab);

        return view;
    }
}
