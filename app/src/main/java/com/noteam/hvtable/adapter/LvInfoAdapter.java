package com.noteam.hvtable.adapter;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.noteam.hvtable.R;
import com.noteam.hvtable.view.MyTextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lunger on 7/13 0013 16:06
 */
public class LvInfoAdapter extends BaseAdapter {
    private static final String TAG = "LvInfoAdapter";
    private Context context;
    int column = -1;
    List<List<String>> data = new ArrayList<>();

    public LvInfoAdapter(Context context) {
        this.context = context;
        for (int i = 1; i < 200; i++) {
            List<String> arrayList = new ArrayList<>();
            for (int j = 1; j < 50; j++) {
                int parseInt = Integer.parseInt("" + i + "" + j);
                if (parseInt > 2000) {
                    parseInt = 2000;
                }
                arrayList.add(parseInt + "");
            }
            data.add(arrayList);
        }

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder2 holder;
        if (convertView == null) {
            holder = new ViewHolder2();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_good_info2, null);
            for (int j = 0; j < data.get(0).size(); j++) {
                MyTextView textView = new MyTextView(context);
                textView.setId(j);
                textView.setHeight(100);
                textView.setMinWidth(200);
                textView.setGravity(Gravity.CENTER);
                textView.setTextSize(16);
                holder.addTextViews(textView);
                ((LinearLayout) convertView).addView(textView);
            }

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder2) convertView.getTag();
        }

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                v.setBackgroundColor(0xFFEFE4E7);
//                selectItem = position;
////                notifyDataSetChanged();
//                Log.e(TAG, "onClick: " + position);
//            }
//        });


        if (selectItem == position) {
            convertView.setBackgroundColor(0xFFEFE4E7);
        } else {
            convertView.setBackgroundColor(0xffffffff);
        }
        if (firstVisibleItem != -1) {
            if (position == 0) {
                return convertView;
            }

        }

//        Log.e(TAG, "getView: " + position + "   " + firstVisibleItem);
        for (int j = 0; j < data.get(position).size(); j++) {
            if (column != -1 && j == column) {
                ((MyTextView) holder.getTextViews().get(j)).setDrawTable(true, data.get(position).get(column));
            } else {
                ((MyTextView) holder.getTextViews().get(j)).setDrawTable(false, data.get(position).get(j));
            }
            Log.w("mj", "getView: " + holder.getTextViews().get(j).getId());
        }


        return convertView;
    }


    int firstVisibleItem = -1;
    int selectItem = -1;

    public void setFirstVisibleItem(int item) {
        this.firstVisibleItem = item;
    }

    public void setColomn(int id) {
        if (column != -1 && id == column) {
            column = -1;
            notifyDataSetChanged();
            return;
        }

        column = id;
        notifyDataSetChanged();
    }

    public void setSelectItem(int position) {
        selectItem = position;
        notifyDataSetChanged();
    }

    private class ViewHolder2 {

        List<TextView> textViews = new ArrayList<>();

        public List<TextView> getTextViews() {
            return textViews;
        }

        public void setTextViews(List<TextView> textViews) {
            this.textViews = textViews;
        }

        public void addTextViews(TextView textViews) {
            this.textViews.add(textViews);
        }
    }


    private class ViewHolder {

        TextView tv_barcode;
        TextView tv_category;
        TextView tv_spec;
        TextView tv_unit;
        TextView tv_supplier;
        TextView tv_sale_money;
        TextView tv_income_money;
        TextView tv_keep;
        TextView tv_intime;
        ImageView tv_online;
        List<TextView> textViews;

        public List<TextView> getTextViews() {
            return textViews;
        }

        public void setTextViews(List<TextView> textViews) {
            this.textViews = textViews;
        }
    }
}
