package com.skullybunny.medinoteservices.medinote.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.skullybunny.medinoteservices.medinote.authenticator.CurrentUser;
import com.skullybunny.medinoteservices.medinote.helpers.EditTextHelpers;
import com.skullybunny.medinoteservices.medinote.webdata.MediNoteWeb;
import com.skullybunny.medinoteservices.medinote.models.MedicalNoteAddDTO;
import com.skullybunny.medinoteservices.medinote.R;

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
        String doctorsNIN =  EditTextHelpers.getTrimmedTextString(mEditTextDoctorsNIN);
        String studentsNIN =  EditTextHelpers.getTrimmedTextString(mEditTextStudentsNIN);
        String men =  EditTextHelpers.getTrimmedTextString(mEditTextMEN);
        String diagnose  =  EditTextHelpers.getTrimmedTextString(mEditTextDiagnose);
        String needs =  EditTextHelpers.getTrimmedTextString(mEditTextNeeds);
        String serveTo = EditTextHelpers.getTrimmedTextString(mEditTextServeTo);
        String date =   EditTextHelpers.getTrimmedTextString(mEditTextDate);

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
