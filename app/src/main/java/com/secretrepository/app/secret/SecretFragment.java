package com.secretrepository.app.secret;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.secretrepository.app.R;

import com.secretrepository.app.database.SecretDatabaseHelper;
import com.secretrepository.app.database.SecretDatabaseHelper.UserBean;
import com.secretrepository.app.database.SecretDatabaseHelper.AddressBean;

import java.util.List;

/**
 * Created by chenguihua on 2016/6/6.
 */
public class SecretFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener{
    private ListView mListView;
    private SecretListAdapter mAdapter;
    private FloatingActionButton mfloatingActionButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.secret_fragment, container, false);
        mListView = (ListView) view.findViewById(R.id.secret_list);
        mfloatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        mfloatingActionButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SecretDatabaseHelper helper = SecretDatabaseHelper.getInstance(getContext());
        mAdapter = new SecretListAdapter(getContext(), helper.addressFindAll());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(getActivity(), SecretEditActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AddressBean bean = (AddressBean) parent.getItemAtPosition(position);
        Intent intent = new Intent(getActivity(), SecretSingleActivity.class);
        intent.putExtra("id", bean.id);
        intent.putExtra("address", bean.address);
        intent.putExtra("website", bean.website);
        startActivity(intent);
    }
}
