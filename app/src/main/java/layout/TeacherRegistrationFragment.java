package layout;

import android.os.Bundle;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.skullybunny.medinoteservices.medinote.R;


public class TeacherRegistrationFragment extends Fragment {

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
       View view = inflater.inflate(R.layout.fragment_teacher_registration, container, false);
        Button btnReg2 = (Button) view.findViewById(R.id.buttonRegisterTeacher);
        btnReg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeFragment(new layout.TeacherFragment(),true);
            }
        });
        return view;
    }


}
