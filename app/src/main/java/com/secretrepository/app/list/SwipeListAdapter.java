package com.secretrepository.app.list;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.WrapperListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguihua on 2016/7/5.
 */
public class SwipeListAdapter implements WrapperListAdapter, SwipeItemLayout.SwipeItemCallbacks{
    private ListAdapter mAdapter;
    private Context mContext;
    private List<SwipeData> datas = new ArrayList<>();

    public SwipeListAdapter(Context context, ListAdapter adapter) {
        mAdapter = adapter;
        mContext = context;
        for(int i = 0; i < adapter.getCount(); i++) {
            SwipeData data = new SwipeData();
            data.mId = adapter.getItemId(i);
            data.data = adapter.getItem(i);
            data.itemStatus = SwipeItemLayout.SWIPE_CLOSE;
            datas.add(data);
        }
    }

    @Override
    public int getCount() {
        return mAdapter.getCount();
    }

    @Override
    public Object getItem(int position) {
        return mAdapter.getItem(position);
    }

    public SwipeData getSwipeItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mAdapter.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SwipeItemLayout layout = null;
        if (convertView == null) {
            View contentView = mAdapter.getView(position, convertView, parent);
            layout = new SwipeItemLayout(getItemId(position), contentView, createMenu(), this);
        } else {
            layout = (SwipeItemLayout)convertView;
        }
        return layout;
    }

    public View createMenu() {
        TextView textView = new TextView(mContext);
        textView.setBackgroundResource(android.R.color.holo_red_dark);
        textView.setText("删除");
        return textView;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        mAdapter.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        mAdapter.unregisterDataSetObserver(observer);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return mAdapter.areAllItemsEnabled();
    }

    @Override
    public boolean isEnabled(int position) {
        return mAdapter.isEnabled(position);
    }

    @Override
    public int getItemViewType(int position) {
        return mAdapter.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return mAdapter.getViewTypeCount();
    }

    @Override
    public boolean isEmpty() {
        return mAdapter.isEmpty();
    }

    @Override
    public boolean hasStableIds() {
        return mAdapter.hasStableIds();
    }

    @Override
    public ListAdapter getWrappedAdapter() {
        return mAdapter;
    }

    @Override
    public void onStatusChange(long mId, int status) {
        for (int i = 0; i < datas.size(); i++) {
            if (mId == datas.get(i).mId) {
                datas.get(i).itemStatus = status;
            }
        }
    }

    public class SwipeData {
        long mId;
        Object data;
        int itemStatus;
    }
}
