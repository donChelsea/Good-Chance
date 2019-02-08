package android.example.com.psychic_app_hw_katsidzira_chelsea.frags;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.example.com.psychic_app_hw_katsidzira_chelsea.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {
    private View rootView;


    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_third, container, false);
        return rootView;
    }

}
