package com.sundy.bbl.ui.aty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;

import android.os.Bundle;
import android.util.Log;

import com.sundy.bbl.R;
import com.sundy.bbl.databinding.BblSingleBindingLayoutBinding;
import com.sundy.bbl.mvvm.model.Goods;

import java.util.Random;

/**
 * 单向数据绑定
 */
public class SingleBindingActivity extends AppCompatActivity {
    private static final String TAG = "SingleBindingActivity";
    private Goods goods;
    BblSingleBindingLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bbl_single_binding_layout);
        goods = new Goods("code", "hi", 24);
        binding.setGoods(goods);
        binding.setGoodHandler(new GoodsHander());
        goods.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (propertyId == com.sundy.bbl.BR.name) {
                    Log.e(TAG, "BR.name");
                } else if (propertyId == com.sundy.bbl.BR.details) {
                    Log.e(TAG, "BR.details");
                } else if (propertyId == com.sundy.bbl.BR._all) {
                    Log.e(TAG, "BR._all");
                } else {
                    Log.e(TAG, "未知: ");
                }
            }
        });
    }


    public class GoodsHander {
        public void changeGoodsName() {
            goods.setName("code" + new Random().nextInt(100));
            goods.setPrice(new Random().nextInt(100));
        }

        public void changeGoodsDetails() {
            goods.setDetails("hi" + new Random().nextInt(100));
            goods.setPrice(new Random().nextInt(100));
        }

    }
}
