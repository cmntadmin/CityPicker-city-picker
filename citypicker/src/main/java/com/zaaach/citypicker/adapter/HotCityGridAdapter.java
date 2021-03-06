package com.zaaach.citypicker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zaaach.citypicker.R;
import com.zaaach.citypicker.model.Area;

import java.util.ArrayList;
import java.util.List;

/**
 * author zaaach on 2016/1/26.
 */
public class HotCityGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<Area> mCities;

    public HotCityGridAdapter(Context context) {
        this.mContext = context;
        mCities = new ArrayList<>();
        mCities.add(new Area("110100", "北京市", "110000", "b", "beijing"));
        mCities.add(new Area("310100", "上海市", "310000", "s", "shanghai"));
        mCities.add(new Area("440100", "广州市", "440000", "g", "guangzhou"));
        mCities.add(new Area("440300", "深圳市", "440000", "s", "shenzhen"));
        mCities.add(new Area("420100", "武汉市", "420000", "w", "wuhan"));
        mCities.add(new Area("120100", "天津市", "120000", "t", "tianjin"));
        mCities.add(new Area("610100", "西安市", "610000", "x", "xian"));
        mCities.add(new Area("320100", "南京市", "320000", "n", "nanjing"));
        mCities.add(new Area("330100", "杭州市", "330000", "h", "hangzhou"));
        mCities.add(new Area("510100", "成都市", "510000", "c", "chengdu"));
        mCities.add(new Area("500100", "重庆市", "500000", "c", "chongqing"));
    }

    @Override
    public int getCount() {
        return mCities == null ? 0 : mCities.size();
    }

    @Override
    public Area getItem(int position) {
        return mCities == null ? null : mCities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        HotCityViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.cp_item_hot_city_gridview, parent, false);
            holder = new HotCityViewHolder();
            holder.name = (TextView) view.findViewById(R.id.tv_hot_city_name);
            view.setTag(holder);
        } else {
            holder = (HotCityViewHolder) view.getTag();
        }
        holder.name.setText(mCities.get(position).getName());
        return view;
    }

    public static class HotCityViewHolder {
        TextView name;
    }
}
