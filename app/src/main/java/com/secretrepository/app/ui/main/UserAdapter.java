package com.secretrepository.app.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.secretrepository.app.R;
import com.secretrepository.app.data.db.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguihua on 2017/3/2.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> implements
        View.OnClickListener, View.OnLongClickListener, View.OnTouchListener{
    final Context mContext;
    private List<User> dataList = new ArrayList<>();

    private final OnItemClickListener mOnItemClickListener;
    private final OnItemLongClickListener mOnItemLongClickListener;

    public UserAdapter(Context context, OnItemClickListener itemListener, OnItemLongClickListener longItemListener) {
        mContext = context;
        mOnItemClickListener = itemListener;
        mOnItemLongClickListener = longItemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, null);
        ViewHolder holder = new ViewHolder(view);
        holder.textView = (TextView)view.findViewById(R.id.item_title);
        view.setOnTouchListener(this);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(dataList.get(position).getDescription());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setData(List<User> data) {
        this.dataList = data;
    }

    public List<User> getData() {
        return dataList;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            int position = (int)v.getTag();
            mOnItemClickListener.onItemClick(v, position, dataList.get(position));
        }
    }

    @Override
    public boolean onLongClick(View v) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_menu, null);
//        PopupWindow popupWin = new PopupWindow(mContext);
//        popupWin.setContentView(view);
//        popupWin.setOutsideTouchable(true);
//        popupWin.setClippingEnabled(true);
//        popupWin.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
//        popupWin.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
//        popupWin.showAsDropDown(v, 0, 0);
        return true;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View view) {
            super(view);
        }

    }

    public static interface OnItemClickListener {
        void onItemClick(View v, int pos, User user);
    }

    public static interface OnItemLongClickListener {
        void onItemLongClick(View v, int pos, User user);
    }

}
