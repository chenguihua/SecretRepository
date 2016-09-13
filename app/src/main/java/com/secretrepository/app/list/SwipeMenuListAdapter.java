package com.secretrepository.app.list;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.WrapperListAdapter;

import com.secretrepository.app.R;

/**
 * Created by chenguihua on 2016/7/5.
 */
public class SwipeMenuListAdapter implements WrapperListAdapter {
    private static final String TAG = "SwipeMenuListAdapter";

    private final ListAdapter mAdapter;
    private Context mContext;


    public SwipeMenuListAdapter(Context context, ListAdapter adapter) {
        mAdapter = adapter;
        mContext = context;
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount() returned: " + mAdapter.getCount());
        return mAdapter.getCount();
    }

    @Override
    public Object getItem(int position) {
        Log.d(TAG, "getItem() returned: " + mAdapter.getItem(position));
        return mAdapter.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return mAdapter.getItemId(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = new SwipeItemLayout(mContext, mAdapter.getView(position, convertView, parent));
            holder.action_btn = (Button) convertView.findViewById(R.id.swipe_button_1);
            convertView.setTag(holder);
        } else {
            View view = mAdapter.getView(position, ((SwipeItemLayout) convertView).getContentView(), parent);
            ((SwipeItemLayout) convertView).setContentView(view);
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    class ViewHolder {
        Button action_btn;
    }

    @Override
    public ListAdapter getWrappedAdapter() {
        return mAdapter;
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
    public void registerDataSetObserver(DataSetObserver observer) {
        mAdapter.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        mAdapter.unregisterDataSetObserver(observer);
    }

    @Override
    public boolean hasStableIds() {
        return mAdapter.hasStableIds();
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

}
