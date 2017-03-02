package com.secretrepository.app.main;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretrepository.app.R;
import com.secretrepository.app.database.SecretDatabaseHelper;
import com.secretrepository.app.database.SecretDatabaseHelper.AddressBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguihua on 2016/6/6.
 */
public class SecretListAdapter extends BaseAdapter implements Filterable{
    private Context mContext;
    private final LayoutInflater mInflater;
    private List<AddressBean> mData;

    private List<AddressBean> mFilterData;
    private MyFilter myFilter;

    private final Object mLock = new Object();

    public SecretListAdapter(Context context, List<AddressBean> data) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mData = data;
    }

    public void update(List<AddressBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        if (myFilter == null) {
            myFilter = new MyFilter();
        }
        return myFilter;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.data_item, null);
//            holder.imageView = (ImageView)view.findViewById(R.id.item_image);
//            holder.title = (TextView)view.findViewById(R.id.item_title);
//            holder.description = (TextView)view.findViewById(R.id.item_description);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.title.setText(mData.get(position).address);
        if (!TextUtils.isEmpty(mData.get(position).website)) {
            holder.description.setText("网址： " + mData.get(position).website);
        }
        return view;
    }

    public final class ViewHolder {
        ImageView imageView;
        TextView title;
        TextView description;
    }

    public class MyFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            mData = SecretDatabaseHelper.getInstance(mContext).addressFindAll();

            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                synchronized (mLock) {
                    ArrayList<AddressBean> list = new ArrayList<>(mData);
                    results.values = list;
                    results.count = list.size();
                }
            } else {
                String prefixString = constraint.toString().toLowerCase();
                ArrayList<AddressBean> values = new ArrayList<>();
                for (int i = 0; i < mData.size(); i++) {
                    if (mData.get(i).address.startsWith(prefixString)) {
                        values.add(mData.get(i));
                    }
                }
                results.values = values;
                results.count = values.size();

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mData =(List<AddressBean>) results.values;
            notifyDataSetChanged();
        }
    }

}
