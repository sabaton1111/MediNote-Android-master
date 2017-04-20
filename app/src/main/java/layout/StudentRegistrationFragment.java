package layout;

import android.app.Activity;
import android.os.Bundle;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.skullybunny.medinoteservices.medinote.CurrentUser;
import com.skullybunny.medinoteservices.medinote.MediNoteWeb;
import com.skullybunny.medinoteservices.medinote.MediNoteWebAPI;
import com.skullybunny.medinoteservices.medinote.R;
import com.skullybunny.medinoteservices.medinote.Student;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StudentRegistrationFragment extends Fragment {
    Activity mActivity;
    MediNoteWebAPI mMediNoteWebAPI;
    Button mBtnRegister;
    EditText mEditTextName;
    EditText mEditTextAge;
    EditText mEditTextAddress;
    EditText mEditTextNIN;
    private void ChangeFragment(Fragment fragment, boolean addReverseTransaction)
    {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        if (addReverseTransaction)
        {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
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
                HandleRegisterClick();
            }
        });
        return view;
    }

    private void HandleRegisterClick()
    {
        String studentNIN = mEditTextNIN.getText().toString();
        int studentAge = Integer.parseInt(mEditTextAge.getText().toString());
        String studentAddress = mEditTextAddress.getText().toString();
        String studentName = mEditTextName.getText().toString();

        Student student = new Student(studentName, studentAge, studentAddress, studentNIN);
        Call<Void> registerStudentCall = mMediNoteWebAPI.registerStudent(student, CurrentUser.getUser().getAuthorization());
        registerStudentCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(mActivity, "Student registered successfully", Toast.LENGTH_SHORT).show();
                    ChangeFragment(new StudentFragment(), true);
                }
                else
                {
                    try {
                        Toast.makeText(mActivity, response.code() + ": " + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    }
                    catch (IOException exception)
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
}
