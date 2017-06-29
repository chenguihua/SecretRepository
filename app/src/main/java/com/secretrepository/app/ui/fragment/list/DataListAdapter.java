package com.secretrepository.app.ui.fragment.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.secretrepository.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguihua on 2017/3/2.
 */

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
//    private List<LoginMsg> msgList = new ArrayList<>();
//    OnRecyclerViewItemClickListener mItemClickListener = null;
//    OnRecyclerViewItemLongClickListener mItemLongClickListener  = null;

    public DataListAdapter() {

    }

//    public DataListAdapter(List<LoginMsg> list) {
//        this.msgList = list;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, null);
        ViewHolder holder = new ViewHolder(view);
        holder.textView = (TextView)view.findViewById(R.id.item_title);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.textView.setText(msgList.get(position).getTitle());
//        holder.itemView.setTag(msgList.get(position));
    }

    @Override
    public int getItemCount() {
        //return msgList.size();
        return 0;
    }

    @Override
    public void onClick(View v) {
//        if (mItemClickListener != null) {
//            mItemClickListener.onItemClick(v, (LoginMsg) v.getTag());
//        }
    }

    @Override
    public boolean onLongClick(View v) {
//        if (mItemLongClickListener != null) {
//            mItemLongClickListener.onItemLongClick(v, (LoginMsg) v.getTag());
//        }
        return true;
    }

//    public void update(List<LoginMsg> list) {
//        //msgList = list;
//        notifyDataSetChanged();
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View view) {
            super(view);
        }

    };

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        //mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnRecyclerViewItemLongClickListener listener) {
        //mItemLongClickListener = listener;
    }

    public static interface OnRecyclerViewItemClickListener {
        //void onItemClick(View view, LoginMsg msg);
    }

    public static interface OnRecyclerViewItemLongClickListener{
        //void onItemLongClick(View view, LoginMsg msg);
    }

}
