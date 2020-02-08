package com.sundy.bbl.ui.aty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.sundy.bbl.R;
import com.sundy.bbl.databinding.BblXmlBindingBeanLayoutBinding;
import com.sundy.bbl.mvvm.model.User;

public class XmlBindingBeanActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BblXmlBindingBeanLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.bbl_xml_binding_bean_layout);
        binding.setUserInfo(user);
        binding.tvText.setText("xml观测");
    }
}
