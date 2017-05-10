package com.skullybunny.medinoteservices.medinote.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.skullybunny.medinoteservices.medinote.R;
import com.skullybunny.medinoteservices.medinote.fragments.DoctorRegistrationFragment;
import com.skullybunny.medinoteservices.medinote.fragments.StudentRegistrationFragment;

public class StartFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        final RadioButton radioBtnDoctor = (RadioButton) view.findViewById(R.id.radioButtonDoctor);
        final RadioButton radioBtnRegisterStudent = (RadioButton) view.findViewById(R.id.radioButtonStudent);
        //final RadioButton teacher = (RadioButton) view.findViewById(R.id.radioTeacher);
        // final RadioButton student = (RadioButton) view.findViewById(R.id.radioButtonStudent);
        Button next = (Button) view.findViewById(R.id.buttonNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioBtnDoctor.isChecked()) {
                    ChangeFragment(new DoctorRegistrationFragment(), true);
                }
                else {
                    ChangeFragment(new StudentRegistrationFragment(), true);
                }

            }
        });

        return view;
    }


}
