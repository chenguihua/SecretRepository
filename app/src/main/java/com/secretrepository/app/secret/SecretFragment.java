package com.secretrepository.app.secret;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.secretrepository.app.R;

/**
 * Created by chenguihua on 2016/6/6.
 */
public class SecretFragment extends Fragment{
    private ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.secret_fragment, container, false);
        mListView = (ListView)view.findViewById(R.id.secret_list);
        return view;
    }
}
