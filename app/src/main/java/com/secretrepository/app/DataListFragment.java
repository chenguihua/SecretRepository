package com.secretrepository.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretrepository.app.data.LoginMsg;
import com.secretrepository.app.data.source.LoginMsgDataSource;
import com.secretrepository.app.data.source.LoginMsgRepository;
import com.secretrepository.app.data.source.local.LoginMsgLocalDataSource;
import com.secretrepository.app.input.EditActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguihua on 2017/3/2.
 */

public class DataListFragment extends Fragment{
    private static final String TAG = "DataListFragment";
    private static final boolean DEBUG = false;
    RecyclerView recyclerView;
    DataListAdapter dataListAdapter;
    FloatingActionButton fabAdd;

    LoginMsgRepository msgRepository;

    public DataListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_datalist, container, false);
        recyclerView = (RecyclerView) contentView.findViewById(R.id.list_data);
        fabAdd = (FloatingActionButton) getActivity().findViewById(R.id.fab_addData);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFabClick();
            }
        });

        return contentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        msgRepository = LoginMsgRepository.getInstance(LoginMsgLocalDataSource.getInstance(getContext().getApplicationContext()));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        updateListView();
    }

    public void onFabClick() {
        Intent intent = new Intent(getContext(), EditActivity.class);
        startActivityForResult(intent, EditActivity.REQUEST_CODE);
    }

    public void updateListView() {
        msgRepository.getAllMsgTitle(new LoginMsgDataSource.LoadAllMsgTitleCallBack() {
            @Override
            public void onDataLoaded(List<LoginMsg> msgList) {
                //dataListAdapter = new DataListAdapter(msgList);
                //recyclerView.setAdapter(dataListAdapter);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            LoginMsg msg = (LoginMsg) data.getSerializableExtra("LoginMsg");
            msgRepository.insertLoginMsg(msg);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
