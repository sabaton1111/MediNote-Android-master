package com.skullybunny.medinoteservices.medinote;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateMedicalNoteFragment extends BaseFragment {

    EditText mEditTextDoctorsNIN;
    EditText mEditTextStudentsNIN;
    EditText mEditTextMEN;
    EditText mEditTextDiagnose;
    EditText mEditTextNeeds;
    EditText mEditTextServeTo;
    EditText mEditTextDate;
    Button mBtnCreateMedicalNote;
    MedicalNoteAddDTO mMedicalNoteAddDTO;

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

        return view;
    }

    @Override
    protected void initializeViews(View view)
    {
        mEditTextDoctorsNIN = (EditText) view.findViewById(R.id.editTextDoctorsNIN);
        mEditTextStudentsNIN = (EditText) view.findViewById(R.id.editTextStudentsNIN);
        mEditTextMEN = (EditText) view.findViewById(R.id.editTextMENCreate);
        mEditTextDiagnose = (EditText) view.findViewById(R.id.editTextDiagnoseCreate);
        mEditTextNeeds = (EditText) view.findViewById(R.id.editTextNeedsCreate);
        mEditTextServeTo = (EditText) view.findViewById(R.id.editTextServeCreateNote);
        mEditTextDate = (EditText) view.findViewById(R.id.editTextDataCreateNote);
        mBtnCreateMedicalNote = (Button) view.findViewById(R.id.buttonCreateNote);
    }

    private void handleCreateMedicalNoteClick()
    {
        mMedicalNoteAddDTO = new MedicalNoteAddDTO();
        fillMedicalNote();
        addMedicalNote();
    }

    private void fillMedicalNote()
    {
        String doctorsNIN =  mEditTextDoctorsNIN.getText().toString();
        String studentsNIN =  mEditTextStudentsNIN.getText().toString();
        String men =  mEditTextMEN.getText().toString();
        String diagnose  =  mEditTextDiagnose.getText().toString();
        String needs =  mEditTextNeeds.getText().toString();
        String serveTo =  mEditTextServeTo.getText().toString();
        String date =  mEditTextDate.getText().toString();

        mMedicalNoteAddDTO = new MedicalNoteAddDTO(doctorsNIN, studentsNIN, needs, serveTo, date, diagnose, men);
    }

    private void addMedicalNote()
    {
        Call<Void> call = MediNoteWeb.getWebAPIInstance().addMedicalNote(mMedicalNoteAddDTO, CurrentUser.getUser().getAuthorization());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(mActivity, "Added medical note", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try {
                        Toast.makeText(mActivity, response.code() + ": " + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
