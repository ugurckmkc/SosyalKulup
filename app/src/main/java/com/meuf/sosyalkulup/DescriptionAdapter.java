package com.meuf.sosyalkulup;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by faruk on 8.05.2017.
 */

public class DescriptionAdapter extends RecyclerView.Adapter<DescriptionAdapter.ViewHolder>{

    private Context context;
    private List<MyDescriptionData> my_data;

    public DescriptionAdapter(Context context,List<MyDescriptionData> my_data)
    {
        this.context=context;
        this.my_data=my_data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_kulup_list_description,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(my_data.get(position).getTitle());
        Glide.with(context).load(my_data.get(position).getClubPics()).into(holder.clubPics);
        holder.description.setText(my_data.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public ImageView clubPics;
        public TextView description;

        public ViewHolder(View itemView)
        {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.txtTitle);
            clubPics=(ImageView) itemView.findViewById(R.id.clubPic);
            description=(TextView) itemView.findViewById(R.id.txtDescription);

        }
    }
}
