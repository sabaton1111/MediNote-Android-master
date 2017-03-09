package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.blajko7.medinote.R;
import com.example.blajko7.medinote.Registration_fragment;

public class StartFragment extends Fragment {

    private void ChangeFragment(Fragment fragment, boolean addReverseTransaction)
    {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_home, fragment);
        if (addReverseTransaction)
        {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        final RadioButton doctor = (RadioButton) view.findViewById(R.id.radioButtonDoctor);
        final RadioButton teacher = (RadioButton) view.findViewById(R.id.radioTeacher);
        final RadioButton medicine_sister = (RadioButton) view.findViewById(R.id.radioButtonMedicineSister);
       // final RadioButton student = (RadioButton) view.findViewById(R.id.radioButtonStudent);
        Button next = (Button) view.findViewById(R.id.buttonNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (doctor.isChecked()) {
                    ChangeFragment(new Registration_fragment(), true);
                } else {
                    if (teacher.isChecked()) {
                        ChangeFragment(new layout.TeacherRegistrationFragment(), true);
                    }
                    else if(medicine_sister.isChecked())
                    {
                        ChangeFragment(new layout.MedicineSisterRegistrationFragment(), true);
                    }
                    else { ChangeFragment(new layout.StudentRegistrationFragment(), true);}
                }
            }
        });

        return view;
    }


}
