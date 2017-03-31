package com.meuf.sosyalkulup;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



public class KulupListAdapter extends BaseAdapter{

    Context context;
    List<ListRow> listItems;

    KulupListAdapter(Context context, List<ListRow> listItems){
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listItems.indexOf(getItem(position));
    }

    public class ViewHolder{
        TextView kulup_name;
        ImageView profil_pic_id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.kulup_list_row,null);
            holder = new ViewHolder();
            holder.kulup_name = (TextView) convertView.findViewById(R.id.Kulup_name);
            holder.profil_pic_id = (ImageView) convertView.findViewById(R.id.kulup_logo);

            ListRow list_pos = listItems.get(position);

            holder.profil_pic_id.setImageResource(list_pos.getProfil_pic_id());
            holder.kulup_name.setText(list_pos.getKulup_name());
        }

        return null;
    }
}
