package com.skullybunny.medinoteservices.medinote;

import android.animation.ValueAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Note_details_fragment extends Fragment {

    MedicalNote medicalNote;
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

    public void setMedicalNote(MedicalNote medicalNote)
    {
        this.medicalNote = medicalNote;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_details_fragment, container, false);
        initializeViews(view);
        fillMedicalNoteData();

        final ImageView backgroundOne = (ImageView) view.findViewById(R.id.background_one);
        final ImageView backgroundTwo = (ImageView) view.findViewById(R.id.background_two);

        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne.getWidth();
                final float translationX = width * progress;
                backgroundOne.setTranslationX(translationX);
                backgroundTwo.setTranslationX(translationX - width);
            }
        });
        animator.start();

        return view;
    }

    private void initializeViews(View view)
    {
        mTextViewHealthcareFacilityName = (TextView) view.findViewById(R.id.textViewHealthFacility);
        mTextViewStudentName = (TextView) view.findViewById(R.id.textViewName);
        mTextViewAddress = (TextView) view.findViewById(R.id.textViewAddress);
        mTextViewAge = (TextView) view.findViewById(R.id.textViewAge);
        mTextViewNeeds = (TextView) view.findViewById(R.id.textViewNeeds);
        mTextViewServe = (TextView) view.findViewById(R.id.textViewServe);
        mTextViewDoctor = (TextView) view.findViewById(R.id.textViewDoctor);
        mTextViewDate = (TextView) view.findViewById(R.id.textViewDate);
        // mTextViewMEN = (TextView) view.findViewById(R.id.textViewMEN);
        // mTextViewDiagnose = (TextView) view.findViewById(R.id.textViewDiagnose);
    }

    private void fillMedicalNoteData()
    {
        mTextViewHealthcareFacilityName.setText(medicalNote.getFacilityName());
        mTextViewStudentName.setText(medicalNote.getStudentName());
        mTextViewAddress.setText(medicalNote.getStudentAddress());
        mTextViewAge.setText(String.valueOf(medicalNote.getStudentAge()));
        mTextViewNeeds.setText(medicalNote.getNeeds());
        mTextViewServe.setText(medicalNote.getInstitutionName());
        mTextViewDoctor.setText(medicalNote.getDoctorName());
        mTextViewDate.setText(medicalNote.getVisitDate());
       // mTextViewMEN.setText(medicalNote.getMen());
       // mTextViewDiagnose.setText(medicalNote.getDiagnose());
    }
}
