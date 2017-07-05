package com.secretrepository.app.ui.main;

import android.view.View;

import com.secretrepository.app.data.DataManager;
import com.secretrepository.app.data.db.model.User;
import com.secretrepository.app.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenguihua on 2017/7/4.
 */

public class MainPresenter<V extends MainContract.View> extends BasePresenter<V> implements MainContract.Presenter<V> {

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void load() {
        getDataManager().loadUsers().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<User>>() {
            @Override
            public void accept(@NonNull List<User> users) throws Exception {
                getView().updateLoadedData(users);
            }
        });
    }

    @Override
    public void onFabClick(View view) {
        getView().openEditActivity();
    }

    @Override
    public void showUserInformation(User user) {
        getView().openUserActivity(user);
    }
}
