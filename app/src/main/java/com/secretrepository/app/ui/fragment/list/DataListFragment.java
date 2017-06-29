package com.secretrepository.app.ui.fragment.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretrepository.app.R;
import com.secretrepository.app.ui.input.EditActivity;

/**
 * Created by chenguihua on 2017/3/2.
 */

public class DataListFragment extends Fragment {
    private static final String TAG = "DataListFragment";
    private static final boolean DEBUG = false;
    RecyclerView recyclerView;
    DataListAdapter dataListAdapter;
    FloatingActionButton fabAdd;
    FloatingActionButton fabDel;

    private View mSelectView = null;


    public DataListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_datalist, container, false);
        recyclerView = (RecyclerView) contentView.findViewById(R.id.list_data);
        fabAdd = (FloatingActionButton) contentView.findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFabAddClick();
            }
        });
        fabDel = (FloatingActionButton) contentView.findViewById(R.id.fab_delete);
        fabDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFabDelClick();
            }
        });

        return contentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        msgRepository = LoginMsgRepository.getInstance(LoginMsgLocalDataSource.getInstance(getContext().getApplicationContext()));
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        dataListAdapter = new DataListAdapter();
//        recyclerView.setAdapter(dataListAdapter);
//        dataListAdapter.setOnItemClickListener(new DataListAdapter.OnRecyclerViewItemClickListener() {
//            @Override
//            public void onItemClick(View view, LoginMsg msg) {
//                showDetail(msg);
//            }
//        });
//        dataListAdapter.setOnItemLongClickListener(new DataListAdapter.OnRecyclerViewItemLongClickListener() {
//            @Override
//            public void onItemLongClick(View view, LoginMsg msg) {
//                selectDeleteItem(view, msg);
//            }
//        });
//        updateListView();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateListView();
    }

    public void onFabAddClick() {
        Intent intent = new Intent(getContext(), EditActivity.class);
        startActivityForResult(intent, EditActivity.REQUEST_CODE);
    }

    public void onFabDelClick() {
//        if (mSelectMessage != null) {
//            msgRepository.deleteLoginMsg(mSelectMessage.getId());
//            cancelSelectDeleteItem();
//            updateListView();
//        }
    }

    public void updateListView() {
//        msgRepository.getAllMsgTitle(new LoginMsgDataSource.LoadAllMsgTitleCallBack() {
//            @Override
//            public void onDataLoaded(List<LoginMsg> msgList) {
//                dataListAdapter.update(msgList);
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//                dataListAdapter.update(new ArrayList<LoginMsg>());
//            }
//        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == Activity.RESULT_OK) {
//            LoginMsg msg = (LoginMsg) data.getSerializableExtra("LoginMsg");
//            msgRepository.insertLoginMsg(msg);
//            updateListView();
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }

//    public void showDetail(LoginMsg msg) {
//        msgRepository.getMsgDetail(msg.getId(), new LoginMsgDataSource.LoadMsgCallback() {
//            @Override
//            public void onDataLoaded(LoginMsg msg) {
//                Intent intent = new Intent(getContext(), DetailActivity.class);
//                intent.putExtra("login", msg);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onDataNotAvailable() {
//
//            }
//        });
//    }

//    public void selectDeleteItem(View view, LoginMsg msg) {
//        if (mSelectMessage != null) return;
//        view.findViewById(R.id.item_title).setBackgroundResource(R.drawable.ic_select);
//        if (fabAdd.getVisibility() == View.VISIBLE) {
//            fabAdd.setVisibility(View.GONE);
//            fabDel.setVisibility(View.VISIBLE);
//        }
//        mSelectMessage = msg;
//        mSelectView = view;
//    }
//
//    public void cancelSelectDeleteItem() {
//        mSelectView.findViewById(R.id.item_title).setBackgroundResource(R.drawable.ic_normal);
//        if (fabAdd.getVisibility() == View.GONE) {
//            fabAdd.setVisibility(View.VISIBLE);
//            fabDel.setVisibility(View.GONE);
//        }
//        mSelectMessage = null;
//        mSelectView = null;
//    }
//
//    public LoginMsg getSelectMessage() {
//        return mSelectMessage;
//    }
}
