package com.sundy.bbl.db;

import androidx.lifecycle.MediatorLiveData;

import com.sundy.bbl.db.entity.ProductEntity;

import java.util.List;

/**
 * 项目名称：BBLDataBinding
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-18 23:56
 * 描述：
 */
public class DataRepository {
    private static volatile DataRepository sInstance;
  private final AppDatabase mDatabase;

    private MediatorLiveData<List<ProductEntity>> mObservableProducts;

    private DataRepository(final AppDatabase database){
        this.mDatabase=database;
       // mObservableProducts.addSource(mDatabase.mUserDao());
    }

    public static DataRepository getInstance(final AppDatabase database){
        if (sInstance==null){
            synchronized (DataRepository.class){
                if (sInstance==null){
                   // sInstance=new DataRepository();
                }
            }
        }
        return sInstance;
    }
}
