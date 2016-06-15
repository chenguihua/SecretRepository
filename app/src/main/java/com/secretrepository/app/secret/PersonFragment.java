package com.secretrepository.app.secret;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.secretrepository.app.R;

import org.w3c.dom.Text;

/**
 * Created by chenguihua on 2016/6/15.
 */
public class PersonFragment extends Fragment {
    TextView username;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_fragment, container, false);
        username = (TextView) view.findViewById(R.id.username);
        return view;
    }



}
