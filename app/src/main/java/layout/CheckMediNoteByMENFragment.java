package layout;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.MediaRouteActionProvider;
import android.os.Bundle;
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
import com.skullybunny.medinoteservices.medinote.MedicalNote;
import com.skullybunny.medinoteservices.medinote.Note_details_fragment;
import com.skullybunny.medinoteservices.medinote.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckMediNoteByMENFragment extends Fragment {

    Activity mActivity;
    MediNoteWebAPI mMediNoteWebAPI;
    Button mBtnCheck;
    EditText mEditTextMEN;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_check_medi_note_by_men, container, false);
        mBtnCheck = (Button) view.findViewById(R.id.check_button);
        mEditTextMEN = (EditText) view.findViewById(R.id.check_men_editbox);
        mBtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HandleCheckClick();
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mMediNoteWebAPI = MediNoteWeb.getWebAPIInstance();
    }

    private void HandleCheckClick()
    {
        String men = mEditTextMEN.getText().toString();
        if (men.length() != 6)
        {
            Toast.makeText(mActivity, "MEN should be exactly 6 digits long", Toast.LENGTH_SHORT).show();
        }
        Call<MedicalNote> getMedicalNoteCall = mMediNoteWebAPI.getMedicalNoteByMEN(men, CurrentUser.getUser().getAuthorization());
        getMedicalNoteCall.enqueue(new Callback<MedicalNote>() {
            @Override
            public void onResponse(Call<MedicalNote> call, Response<MedicalNote> response) {
                if (response.isSuccessful())
                {
                    MedicalNote medicalNote = response.body();
                    showMedicalNoteDetailsFragment(medicalNote);
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
            public void onFailure(Call<MedicalNote> call, Throwable t) {
                t.printStackTrace();
                //Toast.makeText(mActivity, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showMedicalNoteDetailsFragment(MedicalNote medicalNote)
    {
        Note_details_fragment medicalNoteDetailsFragment = new Note_details_fragment();
        medicalNoteDetailsFragment.setMedicalNote(medicalNote);
        ChangeFragment(medicalNoteDetailsFragment, true);
    }
}
