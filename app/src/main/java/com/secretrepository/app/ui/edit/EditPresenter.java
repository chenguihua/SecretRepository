package com.secretrepository.app.ui.edit;

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

public class EditPresenter<V extends EditContract.View> extends BasePresenter<V> implements EditContract.Presenter<V>{
    private CryptUtil crypt;

    @Inject
    public EditPresenter(DataManager dataManager) {
        super(dataManager);
        crypt = new CryptUtil();
    }

    @Override
    public void editNew(String key, User user) {
        if (AppConstants.ENCRYPT_ENABLE) {
            user.setPassword(crypt.encrypt(key, user.getPassword()));
        }
        getDataManager().insertUser(user).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        getView().onEditFinished();
                    }
                });
    }

    @Override
    public void editOld(String key, User user) {
        getDataManager().updateUser(user).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        getView().onEditFinished();
                    }
                });
    }
}
