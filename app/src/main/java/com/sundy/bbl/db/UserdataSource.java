package com.sundy.bbl.db;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * 项目名称：BBLDataBinding
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-18 20:24
 * 描述：
 */
public interface UserdataSource {
    Flowable<User> getUser();


    Completable insertOrUpdateUser(User user);

    void deleteAllUsers();
}
