package com.example.yangli.news.adapter;

import android.content.Context;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangli.news.R;

import java.util.List;
import java.util.Map;


public class MyAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Map<String, Object>> mData;

    public final class ViewHolder {
        public ImageView img;
        public TextView title;
    }


    public MyAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    //返回数据数量，返回几，就调用几次getView方法
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return mData.size();
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        Log.d("ceshi","getView");
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.new_list, null);
            holder.img = (ImageView) view.findViewById(R.id.img);
            holder.title = (TextView) view.findViewById(R.id.title);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.title.setText((String)mData.get(i).get("title"));
        holder.img.setImageBitmap((Bitmap)mData.get(i).get("pic"));

        return view;
    }

    public void updateData(List<Map<String, Object>> mData){
        this.mData=mData;
        notifyDataSetChanged();
    }


}




