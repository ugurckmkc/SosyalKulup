package com.meuf.sosyalkulup.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.meuf.sosyalkulup.MyData;
import com.meuf.sosyalkulup.R;

import java.util.List;

/**
 * Created by UgurCkmkc on 02/12/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<MyData> my_data;

    public CustomAdapter(Context context, List<MyData> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.clubName.setText(my_data.get(position).getClubName());
        Glide.with(context).load(my_data.get(position).getPics()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView clubName;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            clubName = (TextView) itemView.findViewById(R.id.clubName);
            imageView = (ImageView) itemView.findViewById(R.id.image);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //imageView.setAdjustViewBounds(true);
        }
    }
}