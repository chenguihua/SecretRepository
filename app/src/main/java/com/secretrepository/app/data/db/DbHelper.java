package com.secretrepository.app.data.db;

import com.secretrepository.app.data.db.model.User;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by chenguihua on 2017/6/29.
 */

public interface DbHelper {

    Observable<List<User>>  queryUsers();

    Observable<User>        queryUser(Long id);

    Observable<Long>        insertUser(User user);

    Observable<Boolean>     deleteUser(User user);

    Observable<Boolean>     updateUser(User user);

}
