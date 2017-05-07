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
import com.skullybunny.medinoteservices.medinote.helpers.EditTextHelpers;
import com.skullybunny.medinoteservices.medinote.helpers.ToastHelpers;
import com.skullybunny.medinoteservices.medinote.models.ModelError;
import com.skullybunny.medinoteservices.medinote.webdata.MediNoteWeb;
import com.skullybunny.medinoteservices.medinote.webdata.MediNoteWebAPI;
import com.skullybunny.medinoteservices.medinote.R;
import com.skullybunny.medinoteservices.medinote.models.Student;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;


public class StudentRegistrationFragment extends BaseFragment {
    MediNoteWebAPI mMediNoteWebAPI;
    Button mBtnRegister;
    EditText mEditTextName;
    EditText mEditTextAge;
    EditText mEditTextAddress;
    EditText mEditTextNIN;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMediNoteWebAPI = MediNoteWeb.getWebAPIInstance();
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
        String studentNIN = EditTextHelpers.getTrimmedTextString(mEditTextNIN);
        int studentAge = Integer.parseInt(EditTextHelpers.getTrimmedTextString(mEditTextAge));
        String studentAddress =  EditTextHelpers.getTrimmedTextString(mEditTextAddress);
        String studentName =  EditTextHelpers.getTrimmedTextString(mEditTextName);

        Student student = new Student(studentName, studentAge, studentAddress, studentNIN);
        Call<Void> registerStudentCall = mMediNoteWebAPI.registerStudent(student, CurrentUser.getUser().getAuthorization());
        registerStudentCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(mActivity, "Student registered successfully", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try {
                        //Converter<ResponseBody, ModelError> errorConverter = MediNoteWeb.getResponseConverter(ModelError.class, new Annotation[0]);
                        //ModelError modelError = errorConverter.convert(response.errorBody());
                        //Toast.makeText(mActivity, modelError.getMessage() + ": " + modelError.getModelState(), Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(mActivity, jsonObject.getString("Message") + ": " + jsonObject.getJSONObject("ModelState").getJSONArray("").get(0), Toast.LENGTH_SHORT).show();
                        Toast.makeText(mActivity, response.code() + ": " + response.errorBody().string(), Toast.LENGTH_SHORT).show();

                    }
                    catch (IOException exception)
                    {
                        exception.printStackTrace();
                    }
                    catch (JSONException exception)
                    {
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

    private void handleSuccessfulRegister()
    {
        ToastHelpers.showToast(R.string.student_registered_successfully, mActivity);
    }

    private void handleUnsuccessfulRegister()
    {

    }
}
