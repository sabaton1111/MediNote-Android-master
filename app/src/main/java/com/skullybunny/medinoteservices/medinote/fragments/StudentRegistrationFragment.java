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
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import com.skullybunny.medinoteservices.medinote.authenticator.CurrentUser;
import com.skullybunny.medinoteservices.medinote.constants.Constants;
import com.skullybunny.medinoteservices.medinote.helpers.EditTextHelper;
import com.skullybunny.medinoteservices.medinote.helpers.RetrofitHelper;
import com.skullybunny.medinoteservices.medinote.helpers.ToastHelper;
import com.skullybunny.medinoteservices.medinote.helpers.ValidationHelper;
import com.skullybunny.medinoteservices.medinote.webdata.MediNoteWeb;
import com.skullybunny.medinoteservices.medinote.webdata.MediNoteWebAPI;
import com.skullybunny.medinoteservices.medinote.R;
import com.skullybunny.medinoteservices.medinote.models.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StudentRegistrationFragment extends BaseFragment {

    MediNoteWebAPI mMediNoteWebAPI;
    Button mBtnRegister;
    Validator mValidator;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.NAME_REGEX, messageResId = R.string.name_requirements)
    EditText mEditTextName;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Max(value = 180, messageResId = R.string.age_requirements)
    EditText mEditTextAge;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.ADDRESS_REGEX, messageResId = R.string.address_requirements )
    EditText mEditTextAddress;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.NIN_REGEX, messageResId = R.string.nin_requirements)
    EditText mEditTextNIN;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_registration, container, false);
        mBtnRegister = (Button) view.findViewById(R.id.buttonRegisterStudent);
        mEditTextName = (EditText) view.findViewById(R.id.editTextStudentName);
        mEditTextAge = (EditText) view.findViewById(R.id.editTextStudentAge);
        mEditTextAddress = (EditText) view.findViewById(R.id.editTextStudentAddress);
        mEditTextNIN = (EditText) view.findViewById(R.id.editTextStudentNIN);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRegisterClick();
            }
        });
        return view;
    }

    private void handleRegisterClick()
    {
        mValidator.validate();
    }

    private void registerStudent()
    {
        String studentNIN = EditTextHelper.getTrimmedTextString(mEditTextNIN);
        int studentAge = Integer.parseInt(EditTextHelper.getTrimmedTextString(mEditTextAge));
        String studentAddress =  EditTextHelper.getTrimmedTextString(mEditTextAddress);
        String studentName =  EditTextHelper.getTrimmedTextString(mEditTextName);

        Student student = new Student(studentName, studentAge, studentAddress, studentNIN);
        Call<Void> registerStudentCall = mMediNoteWebAPI.registerStudent(student, CurrentUser.getUser().getAuthorization());
        registerStudentCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                {
                    handleSuccessfulRegistration();
                }
                else
                {
                    handleUnsuccessfulRegistration(response);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private void handleValidationSuccess()
    {
        registerStudent();
    }

    private void handleValidationFailure(List<ValidationError> errors)
    {
        ValidationHelper.setOrShowErrors(errors, mActivity);
    }

    private void handleSuccessfulRegistration()
    {
        ToastHelper.showToast(R.string.student_registered_successfully, mActivity);
    }

    private void handleUnsuccessfulRegistration(Response<Void> response)
    {
        RetrofitHelper.showDialogFromErrorResponse(response, mActivity);
    }
}
