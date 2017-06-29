package com.secretrepository.app.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.secretrepository.app.R;
import com.secretrepository.app.ui.base.BaseActivity;
import com.secretrepository.app.ui.fragment.list.DataListFragment;
import com.secretrepository.app.ui.setting.SettingsActivity;

public class MainActivity extends BaseActivity {
    public final static String TAG = "MainActivity";
    public static final boolean DEBUG = true;
    public static String PREFERENCE_NAME = "Login";

    public static Intent getEntryActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu1);

        FragmentManager fragManager = getSupportFragmentManager();
        DataListFragment fragment = (DataListFragment) fragManager.findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = new DataListFragment();
            fragManager.beginTransaction().add(R.id.contentFrame, fragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        DataListFragment fragment = (DataListFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
//        if (fragment != null && fragment.getSelectMessage() != null)
//        {
//            fragment.cancelSelectDeleteItem();
//            return;
//        }
//        super.onBackPressed();
    }
}
