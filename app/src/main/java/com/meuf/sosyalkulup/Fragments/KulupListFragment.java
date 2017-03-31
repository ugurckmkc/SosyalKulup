package com.meuf.sosyalkulup.Fragments;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.meuf.sosyalkulup.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class KulupListFragment extends Fragment implements AdapterView.OnItemClickListener{

    String member_name = "ali";
    TypedArray profil_pics;

    List<String> listItems;
    ListView mylistview;


    public KulupListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        listItems = new ArrayList<>();
        listItems.add(member_name);

        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_kulup_list, container, false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
