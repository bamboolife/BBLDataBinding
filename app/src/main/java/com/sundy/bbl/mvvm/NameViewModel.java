package com.sundy.bbl.mvvm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * 项目名称：BBLDataBinding
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-31 17:44
 * 描述：
 */
public class NameViewModel extends ViewModel {
    private MutableLiveData<String> mCurrentName;
    private MutableLiveData<List<String>> mNameListData;

    public MutableLiveData<String> getCurrentName() {
        if (mCurrentName==null){
            mCurrentName=new MutableLiveData<>();
        }
        return mCurrentName;
    }



    public MutableLiveData<List<String>> getNameListData() {
        if (mNameListData==null){
            mNameListData=new MutableLiveData<>();
        }
        return mNameListData;
    }


}
