package com.example.editninecaserecyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.editninecaserecyclerview.R;
import com.example.editninecaserecyclerview.model.ApplyTable;

import java.util.List;
/**
 * 九宫格布局适配器
 *
 *
 * create by bthvi  2018/06/01
 */
public class ApplyGridAdapter extends BaseAdapter {
    private List<ApplyTable> mTables;
    private Context mContext;
    private LayoutInflater mInflater;

    public ApplyGridAdapter(List<ApplyTable> mTables, Context context) {
        this.mTables = mTables;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mTables.size();
    }

    @Override
    public Object getItem(int i) {
        return mTables.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup group) {
        View view = null;
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.item_grid_list, group, false);
            viewHolder.mImageView = (ImageView) view.findViewById(R.id.iv_channel_logo);
            viewHolder.mTextView = (TextView) view.findViewById(R.id.tv_channel_name);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mImageView.setImageResource(mTables.get(position).getImgRes());
        viewHolder.mTextView.setText(mTables.get(position).getName());
        return view;
    }

    public static class ViewHolder {
        ImageView mImageView;
        TextView mTextView;
    }
}