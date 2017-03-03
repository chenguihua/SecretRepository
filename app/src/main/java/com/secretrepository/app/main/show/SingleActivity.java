package com.secretrepository.app.main.show;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.secretrepository.app.R;
import com.secretrepository.app.BaseActivity;
//import com.secretrepository.app.database.SecretDatabaseHelper;
//import com.secretrepository.app.database.SecretDatabaseHelper.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenguihua on 2016/6/15.
 */
public class SingleActivity extends BaseActivity {
    private static final String TAG = "tag";
    //List<UserBean> beans;
    List<CardView> mCardList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_fragment);
//        LinearLayout cardContainer = (LinearLayout)findViewById(R.id.card_container);
//        Intent intent = getIntent();
//        int id = intent.getIntExtra("id", 0);
//        String address = intent.getStringExtra("address");
//        getSupportActionBar().setTitle(address);
//        SecretDatabaseHelper helper = SecretDatabaseHelper.getInstance(getApplicationContext());
//        if (id == 0) {
//            Log.e(TAG, "address id not found");
//            return;
//        }
//        beans = helper.userFindByAddressId(id);
//        mCardList = new ArrayList<>();
//        for (int i = 0; i < beans.size(); i++) {
//            CardView cardView = (CardView)LayoutInflater.from(this).inflate(R.layout.person_fragment_item, null);
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            lp.setMargins(15, 10, 15, 10);
//            cardContainer.addView(cardView, lp);
//            mCardList.add(cardView);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (beans.size() > 0) {
//            for (int i = 0; i < beans.size(); i++) {
//                ((TextView)mCardList.get(i).findViewById(R.id.single_username)).setText(beans.get(i).username);
//                ((TextView)mCardList.get(i).findViewById(R.id.single_password)).setText(beans.get(i).password);
//            }
//        }
    }
}
