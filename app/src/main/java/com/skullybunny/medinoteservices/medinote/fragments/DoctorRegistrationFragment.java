package com.skullybunny.medinoteservices.medinote.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.skullybunny.medinoteservices.medinote.authenticator.CurrentUser;
import com.skullybunny.medinoteservices.medinote.models.DoctorRegistrationDTO;
import com.skullybunny.medinoteservices.medinote.helpers.EditTextHelpers;
import com.skullybunny.medinoteservices.medinote.validators.NINTextValidator;
import com.skullybunny.medinoteservices.medinote.webdata.MediNoteWeb;
import com.skullybunny.medinoteservices.medinote.webdata.MediNoteWebAPI;
import com.skullybunny.medinoteservices.medinote.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorRegistrationFragment extends BaseFragment {

    MediNoteWebAPI mMediNoteWebAPI;
    Button mBtnRegister;
    EditText mEditTextName;
    EditText mEditTextUIN;
    EditText mEditTextPosition;
    EditText mEditTextHealthcareFacilityName;
    EditText mEditTextEmail;
    EditText mEditTextPhoneNumber;
    EditText mEditTextNIN;
    EditText mEditTextPassword;
    EditText mEditTextConfirmPassword;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMediNoteWebAPI = MediNoteWeb.getWebAPIInstance();
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

        mEditTextNIN.addTextChangedListener(new NINTextValidator(getString(R.string.nin), mEditTextNIN));

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

    private void handleRegisterClick() {

        String name = EditTextHelpers.getTrimmedTextString(mEditTextName);
        String uin = EditTextHelpers.getTrimmedTextString(mEditTextUIN);
        String position =  EditTextHelpers.getTrimmedTextString(mEditTextPosition);
        String healthcareFacilityName =  EditTextHelpers.getTrimmedTextString(mEditTextHealthcareFacilityName);
        String email =  EditTextHelpers.getTrimmedTextString(mEditTextEmail);
        String phoneNumber =  EditTextHelpers.getTrimmedTextString(mEditTextPhoneNumber);
        String nin =  EditTextHelpers.getTrimmedTextString(mEditTextNIN);
        String password =  EditTextHelpers.getTrimmedTextString(mEditTextPassword);
        String confirmPassword =  EditTextHelpers.getTrimmedTextString(mEditTextConfirmPassword);

        if (password.equals(""))
        {
            Toast.makeText(mActivity, "Password field is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (confirmPassword.equals(""))
        {
            Toast.makeText(mActivity, "ConfirmPassword field is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword))
        {
            Toast.makeText(mActivity, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        DoctorRegistrationDTO doctor = new DoctorRegistrationDTO(name, position, uin,
                healthcareFacilityName, email, phoneNumber, nin, password, confirmPassword);

        Call<Void> registerDoctorCall = mMediNoteWebAPI.registerDoctor(doctor, CurrentUser.getUser().getAuthorization());
        registerDoctorCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mActivity, "Doctor registered successfully", Toast.LENGTH_SHORT).show();
                } else {
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
