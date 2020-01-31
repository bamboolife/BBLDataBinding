package com.sundy.bbl.mvvm.viewmodel;

import androidx.lifecycle.ViewModel;

import com.sundy.bbl.db.entity.User;
import com.sundy.bbl.db.UserdataSource;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * 项目名称：BBLDataBinding
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-18 20:37
 * 描述：
 */
public class UserViewModel extends ViewModel {
    private final UserdataSource mDataSource;

    private User mUser;

    public UserViewModel(UserdataSource dataSourcee){
        mDataSource=dataSourcee;
    }
    public Flowable<String>  getUserName(){
        return mDataSource.getUser()
                .map(user -> {
                    mUser=user;
                    return user.getUserName();
                });
    }


    public Completable updateUserName(final String userName){
        mUser=mUser==null ?new User(userName):new User(mUser.getId(),userName);
        return mDataSource.insertOrUpdateUser(mUser);
    }
}
