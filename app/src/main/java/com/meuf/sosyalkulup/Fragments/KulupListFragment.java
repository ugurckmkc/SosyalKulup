package com.meuf.sosyalkulup.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meuf.sosyalkulup.KulupListAdapter;
import com.meuf.sosyalkulup.ListRow;
import com.meuf.sosyalkulup.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class KulupListFragment extends Fragment {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private KulupListAdapter adapter;
    private List<ListRow> data_list;

    public KulupListFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_kulup_list,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        data_list = new ArrayList<>();
        load_data_from_server(0);

        gridLayoutManager = new GridLayoutManager(KulupListFragment.super.getContext(),2); // buraya bi bak..
        recyclerView.setLayoutManager(gridLayoutManager);


        adapter = new KulupListAdapter(KulupListFragment.super.getContext(),data_list); //Fragment yazan yerde sadece this yazması lazım.
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(gridLayoutManager.findLastCompletelyVisibleItemPosition() == data_list.size()-1){
                    load_data_from_server(data_list.get(data_list.size()-1).getClubId());
                }
            }
        });


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kulup_list, container, false);

    }

    private void load_data_from_server(final int clubId) {
        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://sosyalkulup.hol.es/listClub/listClub.php?id="+integers[0])
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());
                    for(int i=0; i<array.length(); i++){

                        JSONObject object = array.getJSONObject(i);

                        ListRow data = new ListRow(object.getInt("clubId"),object.getString("clubName"),
                                object.getString("pics"));
                        data_list.add(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End of content");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }
        };

        task.execute(clubId);
    }

}
