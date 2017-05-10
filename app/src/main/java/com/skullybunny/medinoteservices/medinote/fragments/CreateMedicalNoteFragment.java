package com.skullybunny.medinoteservices.medinote.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
import com.skullybunny.medinoteservices.medinote.webdata.MediNoteWeb;
import com.skullybunny.medinoteservices.medinote.models.MedicalNoteAddDTO;
import com.skullybunny.medinoteservices.medinote.R;
import com.skullybunny.medinoteservices.medinote.webdata.MediNoteWebAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateMedicalNoteFragment extends BaseFragment {

    MediNoteWebAPI mMediNoteWebAPI;
    MedicalNoteAddDTO mMedicalNoteAddDTO;
    Button mBtnCreateMedicalNote;
    Validator mValidator;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.NIN_REGEX, messageResId = R.string.nin_requirements)
    EditText mEditTextDoctorsNIN;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.NIN_REGEX, messageResId = R.string.nin_requirements)
    EditText mEditTextStudentsNIN;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.MEN_REGEX, messageResId = R.string.men_requirements)
    EditText mEditTextMEN;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.DIAGNOSE_REGEX, messageResId = R.string.diagnose_requirements)
    EditText mEditTextDiagnose;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.NEEDS_REGEX, messageResId = R.string.needs_requirements)
    EditText mEditTextNeeds;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.SERVE_TO_REGEX, messageResId = R.string.serve_to_requirements)
    EditText mEditTextServeTo;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.DATE_REGEX, messageResId = R.string.date_requirements)
    EditText mEditTextDate;


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
    protected void initializeViews(View view) {
        mEditTextDoctorsNIN = (EditText) view.findViewById(R.id.editTextDoctorsNIN);
        mEditTextStudentsNIN = (EditText) view.findViewById(R.id.editTextStudentsNIN);
        mEditTextMEN = (EditText) view.findViewById(R.id.editTextMENCreate);
        mEditTextDiagnose = (EditText) view.findViewById(R.id.editTextDiagnoseCreate);
        mEditTextNeeds = (EditText) view.findViewById(R.id.editTextNeedsCreate);
        mEditTextServeTo = (EditText) view.findViewById(R.id.editTextServeCreateNote);
        mEditTextDate = (EditText) view.findViewById(R.id.editTextDataCreateNote);
        mBtnCreateMedicalNote = (Button) view.findViewById(R.id.buttonCreateNote);
    }

    private void handleCreateMedicalNoteClick() {
        mValidator.validate();
    }

    private void fillMedicalNote() {
        String doctorsNIN = EditTextHelper.getTrimmedTextString(mEditTextDoctorsNIN);
        String studentsNIN = EditTextHelper.getTrimmedTextString(mEditTextStudentsNIN);
        String men = EditTextHelper.getTrimmedTextString(mEditTextMEN);
        String diagnose = EditTextHelper.getTrimmedTextString(mEditTextDiagnose);
        String needs = EditTextHelper.getTrimmedTextString(mEditTextNeeds);
        String serveTo = EditTextHelper.getTrimmedTextString(mEditTextServeTo);
        //String date = EditTextHelper.getTrimmedTextString(mEditTextDate);

        mMedicalNoteAddDTO = new MedicalNoteAddDTO(doctorsNIN, studentsNIN, needs, serveTo, null, diagnose, men);
    }

    private void addMedicalNote() {

        Call<Void> call = MediNoteWeb.getWebAPIInstance().addMedicalNote(mMedicalNoteAddDTO, CurrentUser.getUser().getAuthorization());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                {
                    handleSuccessfulAddition();
                }
                else
                {
                    handleUnsuccessfulAddition(response);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void handleValidationSuccess() {
        fillMedicalNote();
        addMedicalNote();
    }

    private void handleValidationFailure(List<ValidationError> errors) {
        ValidationHelper.setOrShowErrors(errors, mActivity);
    }

    private void handleSuccessfulAddition()
    {
        ToastHelper.showToast(R.string.medical_note_added_successfully, mActivity);
    }

    private void handleUnsuccessfulAddition(Response<Void> response)
    {
        RetrofitHelper.showDialogFromErrorResponse(response, mActivity);
    }
}
