package com.meuf.sosyalkulup.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.meuf.sosyalkulup.MyDescriptionData;
import com.meuf.sosyalkulup.R;

import java.util.List;

/**
 * Created by UgurCkmkc on 02/12/2017.
 */

public class DescriptionAdapter extends RecyclerView.Adapter<DescriptionAdapter.ViewHolder>{

    private Context context;
    private List<MyDescriptionData> MyDescriptionData;

    public DescriptionAdapter(Context context,List<MyDescriptionData> MyDescriptionData)
    {
        this.context=context;
        this.MyDescriptionData = MyDescriptionData;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.descriptioncard,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtTitle.setText(MyDescriptionData.get(position).getTitle());
        Glide.with(context).load(MyDescriptionData.get(position).getclubpics()).into(holder.image);
        holder.txtDescription.setText(MyDescriptionData.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return MyDescriptionData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitle;
        public ImageView image;
        public TextView txtDescription;

       public ViewHolder(View itemView)
        {
            super(itemView);
            image=(ImageView) itemView.findViewById(R.id.image);
            txtTitle =(TextView) itemView.findViewById(R.id.txtTitle);
            txtDescription=(TextView) itemView.findViewById(R.id.txtDescription);

        }
   }
}
