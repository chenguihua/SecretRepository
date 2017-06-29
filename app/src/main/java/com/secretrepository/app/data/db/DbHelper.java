package com.secretrepository.app.data.db;

import com.secretrepository.app.data.db.model.User;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by chenguihua on 2017/6/29.
 */

public interface DbHelper {

    Observable<List<User>> getAllUser();

}
