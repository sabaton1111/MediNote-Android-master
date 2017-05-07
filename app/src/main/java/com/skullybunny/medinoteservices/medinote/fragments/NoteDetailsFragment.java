package com.skullybunny.medinoteservices.medinote.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skullybunny.medinoteservices.medinote.R;
import com.skullybunny.medinoteservices.medinote.fragments.BaseFragment;
import com.skullybunny.medinoteservices.medinote.models.MedicalNote;

public class NoteDetailsFragment extends BaseFragment {

    MedicalNote mMedicalNote;
    TextView mTextViewHealthcareFacilityName;
    TextView mTextViewStudentName;
    TextView mTextViewAddress;
    TextView mTextViewAge;
    TextView mTextViewNeeds;
    TextView mTextViewServe;
    TextView mTextViewDoctor;
    TextView mTextViewDate;
    TextView mTextViewMEN;
    TextView mTextViewDiagnose;

    public void setMedicalNote(MedicalNote mMedicalNote)
    {
        this.mMedicalNote = mMedicalNote;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_details_fragment, container, false);
        initializeViews(view);
        fillMedicalNoteData();

        return view;
    }

    @Override
    protected void initializeViews(View view)
    {
        mTextViewHealthcareFacilityName = (TextView) view.findViewById(R.id.textViewHealthFacility);
        mTextViewStudentName = (TextView) view.findViewById(R.id.textViewName);
        mTextViewAddress = (TextView) view.findViewById(R.id.textViewAddress);
        mTextViewAge = (TextView) view.findViewById(R.id.textViewAge);
        mTextViewNeeds = (TextView) view.findViewById(R.id.textViewNeeds);
        mTextViewServe = (TextView) view.findViewById(R.id.textViewServe);
        mTextViewDoctor = (TextView) view.findViewById(R.id.textViewDoctor);
        mTextViewDate = (TextView) view.findViewById(R.id.textViewDate);
        mTextViewMEN = (TextView) view.findViewById(R.id.textViewMEN);
        mTextViewDiagnose = (TextView) view.findViewById(R.id.textViewDiagnose);
    }

    private void fillMedicalNoteData()
    {
        mTextViewHealthcareFacilityName.setText(mMedicalNote.getFacilityName());
        mTextViewStudentName.setText(mMedicalNote.getStudentName());
        mTextViewAddress.setText(mMedicalNote.getStudentAddress());
        mTextViewAge.setText(String.valueOf(mMedicalNote.getStudentAge()));
        mTextViewNeeds.setText(mMedicalNote.getNeeds());
        mTextViewServe.setText(mMedicalNote.getInstitutionName());
        mTextViewDoctor.setText(mMedicalNote.getDoctorName());
        mTextViewDate.setText(mMedicalNote.getVisitDate());
        mTextViewMEN.setText(mMedicalNote.getMen());
        mTextViewDiagnose.setText(mMedicalNote.getDiagnose());
    }
}
