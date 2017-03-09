package layout;

import android.content.Context;
import android.net.Uri;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.blajko7.medinote.R;


public class PrescriptionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prescription, container, false);

        final EditText drug = (EditText) view.findViewById(R.id.editTextDrugTwo);
        final ImageButton imgbtn1 = (ImageButton) view.findViewById(R.id.imageButtonPlusTwo);
        ImageButton imgbtn2 = (ImageButton) view.findViewById(R.id.imageButtonPlus);

        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
       drug.setVisibility(View.VISIBLE);
          imgbtn1.setVisibility(View.VISIBLE);

            }
        });

        return view;
    }


}
