package com.skullybunny.medinoteservices.medinote.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.skullybunny.medinoteservices.medinote.R;


public class StudentFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.student_fragment, container, false);

        TabHost th = (TabHost)view.findViewById(R.id.tabHostStudent);
        th.setup();
        TabHost.TabSpec latest_tab = th.newTabSpec("Note");
        latest_tab.setContent(R.id.tab1);
        latest_tab.setIndicator("Note");
        th.addTab(latest_tab);
        latest_tab = th.newTabSpec("Medical Check");
        latest_tab.setContent(R.id.tab2);
        latest_tab.setIndicator("Medical Check");
        th.addTab(latest_tab);

        return view;
    }


}
