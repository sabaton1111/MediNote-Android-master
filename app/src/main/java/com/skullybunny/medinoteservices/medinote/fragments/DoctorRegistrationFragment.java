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
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;
import com.skullybunny.medinoteservices.medinote.authenticator.CurrentUser;
import com.skullybunny.medinoteservices.medinote.constants.Constants;
import com.skullybunny.medinoteservices.medinote.helpers.RetrofitHelper;
import com.skullybunny.medinoteservices.medinote.helpers.ToastHelper;
import com.skullybunny.medinoteservices.medinote.helpers.ValidationHelper;
import com.skullybunny.medinoteservices.medinote.models.DoctorRegistrationDTO;
import com.skullybunny.medinoteservices.medinote.helpers.EditTextHelper;
import com.skullybunny.medinoteservices.medinote.webdata.MediNoteWeb;
import com.skullybunny.medinoteservices.medinote.webdata.MediNoteWebAPI;
import com.skullybunny.medinoteservices.medinote.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorRegistrationFragment extends BaseFragment {

    MediNoteWebAPI mMediNoteWebAPI;
    Validator mValidator;
    Button mBtnRegister;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.NAME_REGEX, messageResId = R.string.name_requirements)
    EditText mEditTextName;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.UIN_REGEX, messageResId = R.string.uin_requirements)
    EditText mEditTextUIN;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.POSITION_REGEX, messageResId = R.string.position_requirements)
    EditText mEditTextPosition;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.HEALTHCARE_FACILITY_NAME_REGEX, messageResId = R.string.healthcare_facility_name_requirements)
    EditText mEditTextHealthcareFacilityName;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Email(messageResId = R.string.email_not_valid)
    EditText mEditTextEmail;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.MOBILE_REGEX, messageResId = R.string.phone_not_valid)
    EditText mEditTextPhoneNumber;

    @NotEmpty(messageResId = R.string.field_cannot_be_empty)
    @Pattern(regex = Constants.NIN_REGEX, messageResId = R.string.nin_requirements)
    EditText mEditTextNIN;

    @Password(messageResId = R.string.password_min_length_requirement)
    EditText mEditTextPassword;

    @ConfirmPassword(messageResId = R.string.passwords_do_not_match)
    EditText mEditTextConfirmPassword;

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
        View view = inflater.inflate(R.layout.registration_fragment, container, false);
        initializeViews(view);
        mBtnRegister.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        handleRegisterClick();
                    }
                }
        );

        return view;
    }

    @Override
    protected void initializeViews(View view) {
        super.initializeViews(view);

        mBtnRegister = (Button) view.findViewById(R.id.buttonRegisterDoctor);
        mEditTextName = (EditText) view.findViewById(R.id.editTextDoctorName);
        mEditTextUIN = (EditText) view.findViewById(R.id.editTextUIN);
        mEditTextPosition = (EditText) view.findViewById(R.id.editTextPosition);
        mEditTextHealthcareFacilityName = (EditText) view.findViewById(R.id.editTextHealthFacilityRegister);
        mEditTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
        mEditTextPhoneNumber = (EditText) view.findViewById(R.id.editTextPhoneNumber);
        mEditTextNIN = (EditText) view.findViewById(R.id.editTextDoctorNIN);
        mEditTextPassword = (EditText) view.findViewById(R.id.editTextDoctorPassword);
        mEditTextConfirmPassword = (EditText) view.findViewById(R.id.editTextDoctorPasswordRepeat);
    }

    private void handleRegisterClick()
    {
        mValidator.validate();
    }

    private void registerDoctor()
    {
        String name = EditTextHelper.getTrimmedTextString(mEditTextName);
        String uin = EditTextHelper.getTrimmedTextString(mEditTextUIN);
        String position =  EditTextHelper.getTrimmedTextString(mEditTextPosition);
        String healthcareFacilityName =  EditTextHelper.getTrimmedTextString(mEditTextHealthcareFacilityName);
        String email =  EditTextHelper.getTrimmedTextString(mEditTextEmail);
        String phoneNumber =  EditTextHelper.getTrimmedTextString(mEditTextPhoneNumber);
        String nin =  EditTextHelper.getTrimmedTextString(mEditTextNIN);
        String password =  EditTextHelper.getTrimmedTextString(mEditTextPassword);
        String confirmPassword =  EditTextHelper.getTrimmedTextString(mEditTextConfirmPassword);


        DoctorRegistrationDTO doctor = new DoctorRegistrationDTO(name, position, uin,
                healthcareFacilityName, email, phoneNumber, nin, password, confirmPassword);

        Call<Void> registerDoctorCall = mMediNoteWebAPI.registerDoctor(doctor, CurrentUser.getUser().getAuthorization());
        registerDoctorCall.enqueue(new Callback<Void>() {
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
        registerDoctor();
    }

    private void handleValidationFailure(List<ValidationError> errors)
    {
        ValidationHelper.setOrShowErrors(errors, mActivity);
    }

    private void handleSuccessfulRegistration()
    {
        ToastHelper.showToast(R.string.doctor_registered_successfully, mActivity);
    }

    private void handleUnsuccessfulRegistration(Response<Void> response)
    {
        RetrofitHelper.showDialogFromErrorResponse(response, mActivity);
    }
}
