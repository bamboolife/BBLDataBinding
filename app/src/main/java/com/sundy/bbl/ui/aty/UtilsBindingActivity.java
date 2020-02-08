package com.sundy.bbl.ui.aty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.sundy.bbl.R;
import com.sundy.bbl.databinding.BblUtilsBindingLayoutBinding;
import com.sundy.bbl.db.entity.User;

public class UtilsBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbl_utils_binding_layout);
        BblUtilsBindingLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.bbl_utils_binding_layout);
        User user = new User("0001","sundy.jiang");
        binding.setUser(user);
    }
}
