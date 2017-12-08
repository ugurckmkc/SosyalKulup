package com.meuf.sosyalkulup.Fragments;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.meuf.sosyalkulup.Adapters.NotificationAdapter;
import com.meuf.sosyalkulup.MyNotificationData;
import com.meuf.sosyalkulup.R;
import com.meuf.sosyalkulup.RecyclerItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class KulupDuyuruFragment extends Fragment {

    private NotificationAdapter adapter;
    private List<MyNotificationData> data_list_notification;
    private TextView Title,Message;
    private static final String event_URL ="http://sosyalkulup.hol.es/Event/events.php";

    public OnFragmentInteractionListener mListener;

    public KulupDuyuruFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kulup_duyuru,container,false);
        Title = view.findViewById(R.id.text_title);
        Message = view.findViewById(R.id.text_message);

        RecyclerView recycler_view_event = (RecyclerView) view.findViewById(R.id.recycler_view_event);
        data_list_notification  = new ArrayList<>();

        receiveEvent(0);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recycler_view_event.setLayoutManager(gridLayoutManager);

        adapter = new NotificationAdapter(getContext(),data_list_notification);
        recycler_view_event.setAdapter(adapter);

        recycler_view_event.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(View view,int position){
                        Toast.makeText(getContext(),"Etkinlik Detayları Gösterilecek.",Toast.LENGTH_SHORT).show();
                    }
                })
        );

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
        //if(Title1 != "Etkinlik Bulunamadı")
        events(Title1,Message1);

    }
    private void events(String eventName, String eventDescription){
        String urlsuffix ="?eventName=" + eventName +"&eventDescription=" + eventDescription;
            @SuppressLint("StaticFieldLeak")
            class PushEvent extends AsyncTask<String,Void,String>{
                private ProgressDialog loading;

                @Override
                protected String doInBackground(String... params) {
                    String s = params[0];
                    BufferedReader bufferReader = null;
                    try{
                        URL url= new URL(event_URL + s);
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        bufferReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                        return bufferReader.readLine();

                    }catch (Exception e){
                        return null;
                    }
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(getActivity(),"Lütfen Bekleyiniz",null,true,true);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                  //  Toast.makeText(getContext(),"Internet Bağlantınızı Kontrol Edin",Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            }
            PushEvent pue = new PushEvent();
            pue.execute(urlsuffix);
    }

    private void receiveEvent(final int id){
        @SuppressLint("StaticFieldLeak") AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
            private ProgressDialog loading;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Lütfen Bekleyiniz",null,true,true);
            }
            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://sosyalkulup.hol.es/Event/getEvent.php?id=")
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i=0; i<array.length(); i++){

                        JSONObject object = array.getJSONObject(i);

                        MyNotificationData data_event = new MyNotificationData(object.getInt("id"),object.getString("eventName"), object.getString("eventDescription"));

                        data_list_notification.add(data_event);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
                loading.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        loading.dismiss();
                    }
                }, 1500);
            }
        };

        task.execute(id);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
