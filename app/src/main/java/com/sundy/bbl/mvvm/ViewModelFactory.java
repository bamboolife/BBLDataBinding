package com.sundy.bbl.mvvm;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sundy.bbl.db.UserdataSource;
import com.sundy.bbl.mvvm.viewmodel.UserViewModel;

/**
 * 项目名称：BBLDataBinding
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-18 20:34
 * 描述：
 */
public class ViewModelFactory implements ViewModelProvider.Factory{
    private final UserdataSource mDataSource;

    public ViewModelFactory(UserdataSource dataSource){
        mDataSource=dataSource;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class)){
            return (T)new UserViewModel(mDataSource);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
