package com.sundy.bbl.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sundy.bbl.ui.aty.BothwayBindingActivity;
import com.sundy.bbl.ui.aty.EventBindingActivity;
import com.sundy.bbl.ui.aty.FontBindingActivity;
import com.sundy.bbl.ui.aty.ResourceBindingActivity;
import com.sundy.bbl.mvvm.model.CommonBean;
import com.sundy.bbl.ui.aty.SingleBindingActivity;
import com.sundy.bbl.ui.aty.UtilsBindingActivity;
import com.sundy.bbl.ui.aty.ViewStubActivity;
import com.sundy.bbl.ui.aty.XmlBindingBeanActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<CommonBean>> datas;

    public HomeViewModel() {
        datas = new MutableLiveData<>();
        List<CommonBean> beans=new ArrayList<>();
        beans.add(new CommonBean("资源绑定", ResourceBindingActivity.class));
        beans.add(new CommonBean("使用DataBinding来进行字体的自定义", FontBindingActivity.class));
        beans.add(new CommonBean("工具类绑定", UtilsBindingActivity.class));
        beans.add(new CommonBean("ViewStub", ViewStubActivity.class));
        beans.add(new CommonBean("布局绑定model",XmlBindingBeanActivity.class));
        beans.add(new CommonBean("单向数据绑定", SingleBindingActivity.class));
        beans.add(new CommonBean("双向数据绑定", BothwayBindingActivity.class));
        beans.add(new CommonBean("事件绑定", EventBindingActivity.class));
        datas.setValue(beans);
    }

    public MutableLiveData<List<CommonBean>> getDatas() {
        return datas;
    }
}