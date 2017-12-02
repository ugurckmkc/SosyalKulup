package com.meuf.sosyalkulup.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meuf.sosyalkulup.MyNotificationData;
import com.meuf.sosyalkulup.R;

import java.util.List;

/**
 * Created by UgurCkmkc on 02/12/2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context context;
    private List<MyNotificationData> my_notification_data;

    public NotificationAdapter(Context context, List<MyNotificationData> my_notification_data) {
        this.context=context;
        this.my_notification_data = my_notification_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notificationcard,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.eventName.setText(my_notification_data.get(position).getEventName());
        holder.eventDescription.setText(my_notification_data.get(position).getEventDescription());
       // holder.eventName.setText("Ali");
       // holder.eventDescription.setText("Ata Bak");
    }

    // 1'i my_notification_data.size() olarak değiştir
    @Override
    public int getItemCount() {
        return my_notification_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView eventName;
        public TextView eventDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            eventName = (TextView) itemView.findViewById(R.id.eventName);
            eventDescription = (TextView) itemView.findViewById(R.id.eventDescription);
        }
    }
}