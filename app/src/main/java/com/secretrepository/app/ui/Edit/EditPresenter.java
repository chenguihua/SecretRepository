package com.secretrepository.app.ui.Edit;

import com.secretrepository.app.data.DataManager;
import com.secretrepository.app.data.db.model.User;
import com.secretrepository.app.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chenguihua on 2017/7/5.
 */

public class EditPresenter<V extends EditContract.View> extends BasePresenter<V> implements EditContract.Presenter<V>{

    @Inject
    public EditPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void submit(String username, String password, String description) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setDescription(description);
        getDataManager().saveUser(user).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        getView().onEditFinished();
                    }
                });
    }
}
