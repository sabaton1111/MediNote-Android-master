package com.skullybunny.medinoteservices.medinote.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skullybunny.medinoteservices.medinote.R;

public class NoteListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_list_fragment, container, false);
        return view;
    }
}
