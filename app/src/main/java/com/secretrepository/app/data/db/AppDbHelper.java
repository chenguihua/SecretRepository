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
    public Observable<List<User>> queryUsers() {
        return Observable.fromCallable(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                return mDaoSession.getUserDao().loadAll();
            }
        });
    }

    @Override
    public Observable<User> queryUser(final Long id) {
        return Observable.fromCallable(new Callable<User>() {
            @Override
            public User call() throws Exception {
                return mDaoSession.getUserDao().load(id);
            }
        });
    }

    @Override
    public Observable<Long> insertUser(final User user) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mDaoSession.getUserDao().insert(user);
            }
        });
    }

    @Override
    public Observable<Boolean> deleteUser(final User user) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
               mDaoSession.getUserDao().deleteByKey(user.getId());
                return Boolean.TRUE;
            }
        });
    }

    @Override
    public Observable<Boolean> updateUser(final User user) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mDaoSession.getUserDao().update(user);
                return Boolean.TRUE;
            }
        });
    }
}
