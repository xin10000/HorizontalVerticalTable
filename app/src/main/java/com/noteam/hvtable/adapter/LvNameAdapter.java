package com.noteam.hvtable.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.noteam.hvtable.R;


/**
 * Created by Lunger on 7/13 0013 16:05
 */
public class LvNameAdapter extends BaseAdapter {
    private Context context;
    private int selectItem = -1;

    public LvNameAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 199;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    boolean init = true;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_good_name, null);
            holder.tv_goodname = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_goodname.setText("iPone" + (position + 1) + "s");
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setBackgroundColor(0xFFEFE4E7);
//                Log.e(TAG, "onClick: " + position);
//            }
//        });


        if (selectItem == position) {
            convertView.setBackgroundColor(0xFFEFE4E7);
        } else {
            convertView.setBackgroundColor(0xffffffff);
        }

        return convertView;
    }

    public void setSelectItem(int position) {
        selectItem = position;
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView tv_goodname;
    }
}