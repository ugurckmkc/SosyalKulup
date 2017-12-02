package com.meuf.sosyalkulup;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.meuf.sosyalkulup.Adapters.DescriptionAdapter;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class KulupListDescription extends Activity {


    private RecyclerView recycler_view_desc;
    private GridLayoutManager gridLayoutManager;
    private DescriptionAdapter adapter;
    private List<MyDescriptionData> data_list_desc;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kulup_list_description);

        int CLUB_ID = getIntent().getIntExtra("CLUB_ID",0);

        load_data_from_server(CLUB_ID);//send club id from back activity

        recycler_view_desc = (RecyclerView) findViewById(R.id.recycler_view_desc);
        data_list_desc = new ArrayList<>();

        gridLayoutManager = new GridLayoutManager(this,1);
        recycler_view_desc.setLayoutManager(gridLayoutManager);

        adapter = new DescriptionAdapter(this,data_list_desc);
        recycler_view_desc.setAdapter(adapter);

        button = (Button) findViewById(R.id.btn_kayıt);
        // Buradan devam edeceğiz
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });


    }

    private void load_data_from_server(final int id)
    {
        @SuppressLint("StaticFieldLeak") AsyncTask<Integer,Void,Void> task= new AsyncTask<Integer, Void, Void>() {
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

                        JSONObject object = array.getJSONObject(id);

                        MyDescriptionData dataDesc = new MyDescriptionData(object.getInt("clubInfoId"), object.getString("title"), object.getString("description"), object.getInt("clubId"), object.getString("clubpics"));

                        data_list_desc.add(dataDesc);


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
                //TextView denemeTxt = (TextView) findViewById(R.id.deneme_txt);
                //denemeTxt.setText(data_list.get(0).getTitle());

                adapter.notifyDataSetChanged();
                loading.dismiss();
               /* TextView txtTitle=(TextView)findViewById(R.id.txtTitle);
                TextView txtDescription=(TextView) findViewById(R.id.txtDescription);
                ImageView clubPics = (ImageView) findViewById(R.id.clubPic);

                txtTitle.setText(data_list.get(0).getTitle());
                Glide.with(getApplicationContext()).load(data_list.get(0).getClubPics()).into(clubPics);
                txtDescription.setText(data_list.get(0).getDescription());
*/
            }
        };
        task.execute(id);
    }



}
