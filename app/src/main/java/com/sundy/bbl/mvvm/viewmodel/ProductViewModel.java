package com.sundy.bbl.mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sundy.bbl.db.DataRepository;
import com.sundy.bbl.db.entity.CommentEntity;
import com.sundy.bbl.db.entity.ProductEntity;

import java.util.List;

/**
 * 项目名称：BBLDataBinding
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-18 23:48
 * 描述：
 */
public class ProductViewModel extends AndroidViewModel {
    private final LiveData<ProductEntity> mObservableProduct;
    public ObservableField<ProductEntity> product=new ObservableField<>();
    private final int mProductId;
    private final LiveData<List<CommentEntity>> mObservableComments;

    public ProductViewModel(@NonNull Application application, DataRepository repository,final int productId) {
        super(application);
        mProductId=productId;
        mObservableProduct=new MutableLiveData<>();
        mObservableComments=new MutableLiveData<>();
    }

    /**
     * Expose the LiveData Comments query so the UI can observe it.
     */
    public LiveData<List<CommentEntity>> getComments() {
        return mObservableComments;
    }

    public LiveData<ProductEntity> getObservableProduct() {
        return mObservableProduct;
    }

    public void setProduct(ProductEntity product) {
        this.product.set(product);
    }
}
