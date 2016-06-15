package com.secretrepository.app.secret;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretrepository.app.R;
import com.secretrepository.app.database.SecretDatabaseHelper.AddressBean;

import java.util.List;

/**
 * Created by chenguihua on 2016/6/6.
 */
public class SecretListAdapter extends BaseAdapter{
    private final LayoutInflater mInflater;
    private final List<AddressBean> mData;

    public SecretListAdapter(Context context, List<AddressBean> data) {
        mInflater = LayoutInflater.from(context);
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public AddressBean getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.address_item, null);
            holder.imageView = (ImageView)view.findViewById(R.id.item_image);
            holder.textView = (TextView)view.findViewById(R.id.item_title);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(mData.get(i).address);
        return view;
    }

    public final class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

}
