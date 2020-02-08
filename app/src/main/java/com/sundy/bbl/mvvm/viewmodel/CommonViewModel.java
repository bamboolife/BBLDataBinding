package com.sundy.bbl.mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.sundy.bbl.mvvm.model.CommonBean;

public class CommonViewModel extends AndroidViewModel {
    private LiveData<CommonBean> mObservableCommonBean;

    public CommonViewModel(@NonNull Application application,LiveData<CommonBean> mObservableCommonBean) {
        super(application);
        mObservableCommonBean=mObservableCommonBean;
    }

    public LiveData<CommonBean> getmObservableCommonBean() {
        return mObservableCommonBean;
    }
}
