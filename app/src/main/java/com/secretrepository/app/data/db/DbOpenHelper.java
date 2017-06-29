package com.secretrepository.app.data.db;

import android.content.Context;

import com.secretrepository.app.data.db.model.DaoMaster;
import com.secretrepository.app.di.ApplicationContext;
import com.secretrepository.app.di.DatabaseInfo;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by chenguihua on 2017/6/29.
 */
@Singleton
public class DbOpenHelper extends DaoMaster.DevOpenHelper {

    @Inject
    public DbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
