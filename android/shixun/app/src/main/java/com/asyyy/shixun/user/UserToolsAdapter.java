package com.asyyy.shixun.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.asyyy.shixun.R;

public class UserToolsAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int[] icons;
    private String[] names;

    public UserToolsAdapter(Context context, int[] icons, String[] names) {
        this.mContext = context;
        this.icons = icons;
        this.names = names;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.user_item, parent, false);
        }
        ImageView icon = convertView.findViewById(R.id.user_item_icon);
        TextView name = convertView.findViewById(R.id.user_item_name);

        icon.setImageResource(icons[position]); // 使用setImageResource而不是setBackgroundResource
        name.setText(names[position]);
        return convertView;
    }
}
