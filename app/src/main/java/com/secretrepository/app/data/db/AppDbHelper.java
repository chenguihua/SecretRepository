package com.secretrepository.app.data.db;

import com.secretrepository.app.data.db.model.DaoMaster;
import com.secretrepository.app.data.db.model.DaoSession;
import com.secretrepository.app.data.db.model.User;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by chenguihua on 2017/6/29.
 */

@Singleton
public class AppDbHelper implements DbHelper{

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDatabase()).newSession();
    }

    @Override
    public Observable<List<User>> getAllUser() {
        return Observable.fromCallable(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                return mDaoSession.getUserDao().loadAll();
            }
        });
    }
}
