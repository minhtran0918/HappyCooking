package calebzone.hcmute.edu.vn.happycooking.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import calebzone.hcmute.edu.vn.happycooking.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeekFragment extends Fragment {


    public WeekFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_week, container, false);
    }

}
