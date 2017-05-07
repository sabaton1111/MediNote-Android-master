package com.skullybunny.medinoteservices.medinote.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skullybunny.medinoteservices.medinote.R;


public class MedicineSisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.medicine_sister_fragment, container, false);
        return view;
    }


}
