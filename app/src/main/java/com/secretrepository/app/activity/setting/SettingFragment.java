package com.secretrepository.app.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;

import com.secretrepository.app.R;
import com.secretrepository.app.backup.ImportAndExportManager;

import java.io.File;

/**
 * Created by chenguihua on 2017/3/7.
 */

public class SettingFragment extends PreferenceFragment {
    public static final int REQUEST_IMPORT = 1;
    ImportAndExportManager backupManager = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prederence_setting);
        backupManager = new ImportAndExportManager(getActivity());
        initPreference();

    }

    private void initPreference() {
        findPreference("pref_import").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                importData();
                return true;
            }
        });
        findPreference("pref_export").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                exportData();
                return true;
            }
        });
    }

    private void importData() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_IMPORT);
    }

    private void exportData() {

        backupManager.exportDataToSdCard();
        Snackbar.make(getActivity().findViewById(R.id.setting_content), "导出数据成功", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_IMPORT) {
            Uri uri = data.getData();
            String string = uri.toString();
            if (string.indexOf(String.valueOf(Environment.getExternalStorageDirectory()))!=-1){
                String[] contents = string.split(String.valueOf(Environment.getExternalStorageDirectory()));
                File file = new File(Environment.getExternalStorageDirectory(),contents[1]);
                backupManager.importDataFromSdCard(file);
                Snackbar.make(getActivity().findViewById(R.id.setting_content), "导入数据成功", Snackbar.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
