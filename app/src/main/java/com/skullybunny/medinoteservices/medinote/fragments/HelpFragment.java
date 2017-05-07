package com.skullybunny.medinoteservices.medinote.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skullybunny.medinoteservices.medinote.R;

public class HelpFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_help, container, false);
        TextView text = (TextView) view.findViewById(R.id.textViewAboutUsDescription);
      //  text.setText(Html.fromHtml("<p>sammsasamsa</p> <p>sassaasassa</p>"));
    return view;
    }


}
