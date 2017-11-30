package com.meuf.sosyalkulup.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meuf.sosyalkulup.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class KulupDuyuruFragment extends Fragment {

    private TextView Title,Message;

    public OnFragmentInteractionListener mListener;

    public KulupDuyuruFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kulup_duyuru,container,false);

        Title = (TextView) view.findViewById(R.id.text_title);
        Message = (TextView) view.findViewById(R.id.text_message);



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("com.meuf.sosyalkulup", Context.MODE_PRIVATE);

        String Title1 = sharedPref.getString("title", "Etkinlik Bulunamadı");
        String Message1 = sharedPref.getString("message", "Etkinlik açıklaması bulunamadı!");

        Title.setText(Title1);
        Message.setText(Message1);


    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
