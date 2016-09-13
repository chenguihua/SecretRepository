package com.secretrepository.app.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.secretrepository.app.R;
import com.secretrepository.app.backup.RecordExport;
import com.secretrepository.app.main.setting.SettingsActivity;
import com.secretrepository.app.database.SecretDatabaseHelper;
import com.secretrepository.app.list.SwipeMenuListView;
import com.secretrepository.app.main.Input.EditActivity;
import com.secretrepository.app.main.show.SingleActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, Toolbar.OnMenuItemClickListener,
        SearchView.OnQueryTextListener,
        SwipeMenuListView.OnSwipeMenuClickListener {
    public final static String TAG = "MainActivity";
    private static final boolean DEBUG = true;

    public static final String PREFERENCE_NAME = "account";

    private SwipeMenuListView mListView;
    private FloatingActionButton mFloatingActionButton;

    SecretListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this);

        mListView = (SwipeMenuListView) findViewById(R.id.secret_list);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SecretDatabaseHelper helper = SecretDatabaseHelper.getInstance(this);
        mAdapter = new SecretListAdapter(this, helper.addressFindAll());
        mListView.setAdapter(mAdapter);
        mListView.setOnSwipeMenuClickListener(this);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_backup:
                new RecordExport().backup(this);
                break;
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.action_fortest:
                Intent intent_fortest = new Intent(this, ForTestActivity.class);
                startActivity(intent_fortest);
        }
        return true;
    }

    @Override
    public void onSwipeMenuClick(View view, int position) {
        SecretDatabaseHelper helper = SecretDatabaseHelper.getInstance(this);
        helper.deleteByAddress(mAdapter.getItem(position).id);
        mAdapter.update(helper.addressFindAll());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(this, EditActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SecretDatabaseHelper.AddressBean bean = (SecretDatabaseHelper.AddressBean) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, SingleActivity.class);
        intent.putExtra("id", bean.id);
        intent.putExtra("address", bean.address);
        intent.putExtra("website", bean.website);
        startActivity(intent);
    }
}
