package com.sundy.common.mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;

import com.sundy.common.mvvm.model.BaseModel;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 项目名称：BBLDataBinding
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-18 23:31
 * 描述：
 */
public class BaseViewModel<M extends BaseModel> extends AndroidViewModel implements LifecycleObserver, Consumer<Disposable> {
    protected M mModel;
    //Disposable容器
    private CompositeDisposable mDisposables = new CompositeDisposable();

    public BaseViewModel(@NonNull Application application,M model) {
        super(application);
        this.mModel=model;
    }

    @Override
    public void accept(Disposable disposable) {

    }
}
