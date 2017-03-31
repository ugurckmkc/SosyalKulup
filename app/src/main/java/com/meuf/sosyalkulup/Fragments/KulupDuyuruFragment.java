package com.meuf.sosyalkulup.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meuf.sosyalkulup.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class KulupDuyuruFragment extends Fragment {


    public KulupDuyuruFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kulup_duyuru, container, false);
    }

}
