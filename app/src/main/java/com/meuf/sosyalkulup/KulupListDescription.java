package com.meuf.sosyalkulup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;



public class KulupListDescription extends Activity {


   // private DescriptionAdapter adapter;
    private List<MyDescriptionData> data_list=new ArrayList<>();
    private Layout layout;
    Intent intent = getIntent();
    int CLUB_ID=intent.getIntExtra("CLUB_ID",0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kulup_list_description);

        TextView txtTitle=(TextView)findViewById(R.id.txtTitle);
        TextView txtDescription=(TextView) findViewById(R.id.txtDescription);
        ImageView clubPics = (ImageView) findViewById(R.id.clubPic);
        Context context=this;


        load_data_from_server(CLUB_ID);//send club id from back activity
        txtTitle.setText(data_list.get(0).getTitle());
        Glide.with(context).load(data_list.get(0).getClubPics()).into(clubPics);
        txtDescription.setText(data_list.get(0).getDescription());

}

    private void load_data_from_server(final int id)
    {
        AsyncTask<Integer,Void,Void> task= new AsyncTask<Integer, Void, Void>() {
            private ProgressDialog loading;

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                loading = ProgressDialog.show(KulupListDescription.this,
                "Lutfen Bekleyiniz...",null,true,true);
            }

            @Override
            protected Void doInBackground(Integer... params) {

                OkHttpClient client = new OkHttpClient();
                Request request= new Request.Builder()
                        .url("http://sosyalkulup.hol.es/listClub/listDescription.php?id="+id)
                        .build();
                try{
                    Response response = client.newCall(request).execute();
                    JSONArray array = new JSONArray(response.body().string());
                    for(int i=0; i<array.length(); i++)
                    {
                        JSONObject object = array.getJSONObject(i);
                        MyDescriptionData data = new MyDescriptionData(object.getInt("clubId"),object.getString("title"),
                        object.getString("activity_kulup_list_description"),object.getString("clubPics"));

                        data_list.add(data);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid)
            {
                loading.dismiss();
            }
        };
        task.execute(id);
    }
}
