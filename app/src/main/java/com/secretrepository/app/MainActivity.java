package com.secretrepository.app;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends BaseActivity {
    public final static String TAG = "MainActivity";
    public static final boolean DEBUG = true;
    public static String PREFERENCE_NAME = "abc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_1);

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
                break;
            case R.id.action_backup:
                break;
            case R.id.action_debug:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
