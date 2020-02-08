package com.sundy.bbl.ui.aty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.sundy.bbl.R;
import com.sundy.bbl.databinding.ResourceBinding;

public class ResourceBindingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      ResourceBinding binding= DataBindingUtil.setContentView(this, R.layout.bbl_resource_binding_layout);
      binding.setFirstName("sundy");
      binding.setLastName("jiang");
     // binding.setAge(11);
      binding.setOrangeCount(20);
      binding.setLarge(true);
    }
}
