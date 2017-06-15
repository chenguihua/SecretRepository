package com.secretrepository.app.main.activity.setting;

import android.os.Bundle;

import com.secretrepository.app.R;
import com.secretrepository.app.main.BaseActivity;

/**
 * Created by chenguihua on 2016/7/1.
 */
public class SettingsActivity  extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getFragmentManager().beginTransaction().add(R.id.setting_content, new SettingFragment()).commit();
    }
}
