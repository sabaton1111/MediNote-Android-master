package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.blajko7.medinote.R;

public class HelpFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_help, container, false);
        TextView text = (TextView) view.findViewById(R.id.textViewAboutUsDescription);
      //  text.setText(Html.fromHtml("<p>sammsasamsa</p> <p>sassaasassa</p>"));
    return view;
    }


}
