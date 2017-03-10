package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.example.blajko7.medinote.R;


public class StudentFragment extends Fragment {

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
        View view= inflater.inflate(R.layout.student_fragment, container, false);

        TabHost th = (TabHost)view.findViewById(R.id.tabHost);
        th.setup();
        TabHost.TabSpec latest_tab = th.newTabSpec("Note");
        latest_tab.setContent(R.id.tab1);
        latest_tab.setIndicator("Note");
        th.addTab(latest_tab);
        latest_tab = th.newTabSpec("Medical Check");
        latest_tab.setContent(R.id.tab2);
        latest_tab.setIndicator("Medical Check");
        th.addTab(latest_tab);

        return view;
    }


}
