package com.secretrepository.app.secret;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class SecretFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView mListView;
    private SecretListAdapter mAdapter;

    public interface HostInterface {
        public void enterPersonalView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.secret_fragment, container, false);
        mListView = (ListView) view.findViewById(R.id.secret_list);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AddressBean aBean = (AddressBean) parent.getItemAtPosition(position);
        SecretDatabaseHelper helper = SecretDatabaseHelper.getInstance(getContext());
        List<UserBean> uBean = (List<UserBean>) helper.userFindByAddressId(aBean.id);
        ((HostInterface)getActivity()).enterPersonalView();
    }
}
