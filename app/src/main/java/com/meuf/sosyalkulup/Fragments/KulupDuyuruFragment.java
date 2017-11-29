package com.meuf.sosyalkulup.Fragments;


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

        Bundle bundle = getActivity().getIntent().getExtras();

        if(bundle !=null){
            for(String key : bundle.keySet()){
                if(key.equals("title"))
                    Title.setText(bundle.getString(key));
                else if(key.equals("message"))
                    Message.setText(bundle.getString(key));
            }
        }

        return view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
