package com.secretrepository.app.ui.display;

import com.secretrepository.app.data.DataManager;
import com.secretrepository.app.data.db.model.User;
import com.secretrepository.app.ui.base.BasePresenter;
import com.secretrepository.app.util.AppConstants;
import com.secretrepository.app.util.CryptUtil;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenguihua on 2017/7/5.
 */

public class UserPresenter<V extends UserContract.View> extends BasePresenter<V> implements UserContract.Presenter<V> {

    CryptUtil crypt;

    @Inject
    public UserPresenter(DataManager dataManager) {
        super(dataManager);
        crypt = new CryptUtil();
    }

    @Override
    public void show(Long id, final String key) {
        getDataManager().queryUser(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<User>() {
            @Override
            public void accept(@NonNull User user) throws Exception {
                String password = null;
                if (AppConstants.ENCRYPT_ENABLE) {
                    password = crypt.decrypt(key, user.getPassword());
                } else {
                    password = user.getPassword();
                }
                getView().showInfoOnUI(user.getDescription(), user.getUsername(), password);
            }
        });
    }

    @Override
    public void delete(User user) {
        getDataManager().deleteUser(user).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {
                getView().onUserDelete();
            }
        });
    }

    @Override
    public void edit(User user) {
        getView().openEditActivity(user);
    }
}
