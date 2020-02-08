package com.sundy.bbl.adapter;

import com.sundy.bbl.R;
import com.sundy.bbl.databinding.BblCommonItemLayoutBinding;
import com.sundy.bbl.mvvm.model.CommonBean;
import com.sundy.common.base.BaseQuickAdapter;

/**
 * 通用列表适配器
 */
public class CommonAdapter extends BaseQuickAdapter<CommonBean, BblCommonItemLayoutBinding> {


    @Override
    protected void onBindItem(BblCommonItemLayoutBinding binding, CommonBean item) {
        binding.setCommonbean(item);
        binding.executePendingBindings();
    }

    @Override
    protected int onBindLayout(int viewType) {
        return R.layout.bbl_common_item_layout;
    }
}
