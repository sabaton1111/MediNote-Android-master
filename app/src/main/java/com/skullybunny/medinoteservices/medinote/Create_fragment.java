package com.skullybunny.medinoteservices.medinote;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Create_fragment extends Fragment{

    EditText mEditTextHealthFacilityName;
    EditText mEditTextStudentName;
    EditText mEditTextMEN;
    EditText mEditTextAge;
    EditText mEditTextDiagnose;
    EditText mEditTextNeeds;
    EditText mEditTextServeTo;
    EditText mEditTextDoctor;
    EditText mEditTextDate;
    Button mBtnCreateMedicalNote;
    MedicalNoteAddDTO mMedicalNoteAddDTO;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_fragment, container, false);

        initializeViews(view);

        mBtnCreateMedicalNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCreateMedicalNoteClick();
            }
        });
        fillMedicalNote();

        return view;
    }

    private void fillMedicalNote()
    {

    }

    private void initializeViews(View view)
    {
        mEditTextHealthFacilityName = (EditText) view.findViewById(R.id.editTextHealthFacilityCreate);
        mEditTextStudentName = (EditText) view.findViewById(R.id.editTextStudentNameCreate);
        mEditTextMEN = (EditText) view.findViewById(R.id.editTextMENCreate);
        mEditTextAge = (EditText) view.findViewById(R.id.editTextAgeCreate);
        mEditTextDiagnose = (EditText) view.findViewById(R.id.editTextDiagnoseCreate);
        mEditTextNeeds = (EditText) view.findViewById(R.id.editTextNeedsCreate);
        mEditTextServeTo = (EditText) view.findViewById(R.id.editTextServeCreateNote);
        mEditTextDoctor = (EditText) view.findViewById(R.id.editTextDoctorCreateNote);
        mEditTextDate = (EditText) view.findViewById(R.id.editTextDateCreateNote);
        mBtnCreateMedicalNote = (Button) view.findViewById(R.id.buttonCreateNote);
    }

    private void handleCreateMedicalNoteClick()
    {
        mMedicalNoteAddDTO = new MedicalNoteAddDTO();
    }
}
