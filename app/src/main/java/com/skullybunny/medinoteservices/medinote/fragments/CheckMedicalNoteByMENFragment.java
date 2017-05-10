package com.skullybunny.medinoteservices.medinote.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import com.skullybunny.medinoteservices.medinote.authenticator.CurrentUser;
import com.skullybunny.medinoteservices.medinote.constants.Constants;
import com.skullybunny.medinoteservices.medinote.helpers.EditTextHelper;
import com.skullybunny.medinoteservices.medinote.helpers.RetrofitHelper;
import com.skullybunny.medinoteservices.medinote.helpers.ToastHelper;
import com.skullybunny.medinoteservices.medinote.helpers.ValidationHelper;
import com.skullybunny.medinoteservices.medinote.models.ModelError;
import com.skullybunny.medinoteservices.medinote.webdata.MediNoteWeb;
import com.skullybunny.medinoteservices.medinote.webdata.MediNoteWebAPI;
import com.skullybunny.medinoteservices.medinote.models.MedicalNote;
import com.skullybunny.medinoteservices.medinote.R;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class CheckMedicalNoteByMENFragment extends BaseFragment {

    MediNoteWebAPI mMediNoteWebAPI;
    Button mBtnCheck;
    Validator mValidator;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.MEN_REGEX, messageResId = R.string.men_requirements)
    EditText mEditTextMEN;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMediNoteWebAPI = MediNoteWeb.getWebAPIInstance();

        mValidator = new Validator(this);
        mValidator.setValidationListener(new Validator.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                handleValidationSuccess();
            }

            @Override
            public void onValidationFailed(List<ValidationError> errors) {
                handleValidationFailure(errors);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_medi_note_by_men, container, false);
        mBtnCheck = (Button) view.findViewById(R.id.check_button);
        mEditTextMEN = (EditText) view.findViewById(R.id.check_men_editbox);
        mBtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCheckClick();
            }
        });
        return view;
    }

    private void handleCheckClick()
    {
        mValidator.validate();
    }

    private void showMedicalNoteDetailsFragment(MedicalNote medicalNote)
    {
        NoteDetailsFragment medicalNoteDetailsFragment = new NoteDetailsFragment();
        medicalNoteDetailsFragment.setMedicalNote(medicalNote);
        changeFragment(medicalNoteDetailsFragment, true);
    }

    private void retrieveMedicalNote()
    {
        String men =  EditTextHelper.getTrimmedTextString(mEditTextMEN);

        Call<MedicalNote> getMedicalNoteCall = mMediNoteWebAPI.getMedicalNoteByMEN(men, CurrentUser.getUser().getAuthorization());
        getMedicalNoteCall.enqueue(new Callback<MedicalNote>() {
            @Override
            public void onResponse(Call<MedicalNote> call, Response<MedicalNote> response) {
                if (response.isSuccessful())
                {
                    handleSuccessfulRetrieval(response);
                }
                else
                {
                    handleUnsuccessfulRetrieval(response);
                }
            }

            @Override
            public void onFailure(Call<MedicalNote> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void handleValidationSuccess()
    {
        retrieveMedicalNote();
    }

    private void handleValidationFailure(List<ValidationError> errors)
    {
        ValidationHelper.setOrShowErrors(errors, mActivity);
    }

    private void handleSuccessfulRetrieval(Response<MedicalNote> response)
    {
        MedicalNote medicalNote = response.body();
        showMedicalNoteDetailsFragment(medicalNote);
    }

    private void handleUnsuccessfulRetrieval(Response<MedicalNote> response)
    {
        RetrofitHelper.showDialogFromErrorResponse(response, mActivity);
    }
}
