package com.secretrepository.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by chenguihua on 2017/3/2.
 */

public class DataListFragment extends Fragment{
    private static final String TAG = "DataListFragment";
    private static final boolean DEBUG = false;
    RecyclerView recyclerView;
    DataListAdapter dataListAdapter;

    public DataListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_datalist, container, false);
        recyclerView = (RecyclerView) contentView.findViewById(R.id.list_data);
        return contentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        dataListAdapter = new DataListAdapter(new ArrayList<String>());
        recyclerView.setAdapter(dataListAdapter);
    }

}
