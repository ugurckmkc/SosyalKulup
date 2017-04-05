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



public class KulupListAdapter extends RecyclerView.Adapter<KulupListAdapter.ViewHolder>{

    private Context context;
    private List<ListRow> my_data;

    public KulupListAdapter(Context context, List<ListRow> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.kulup_list_row,parent,false);
        return  new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.clubName.setText(my_data.get(position).getClubId());
        Glide.with(context).load(my_data.get(position).getPics()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView clubName;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            clubName = (TextView) itemView.findViewById(R.id.clubName);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
