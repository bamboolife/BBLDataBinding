package com.sundy.bbl.db;

import android.content.Context;

import com.sundy.bbl.mvvm.ViewModelFactory;

/**
 * 项目名称：BBLDataBinding
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-18 20:26
 * 描述：
 */
public class Injection {
    public static UserdataSource provideUserDataSource(Context context){
        AppDatabase database= AppDatabase.getInstance(context);
        return new LocalUserDataSource(database.mUserDao());
    }

    public static ViewModelFactory provideViewModelFactory(Context context){
        UserdataSource dataSource=provideUserDataSource(context);
        return new ViewModelFactory(dataSource);
    }
}
