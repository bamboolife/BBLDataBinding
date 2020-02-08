package com.sundy.bbl.ui.aty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import com.sundy.bbl.R;
import com.sundy.bbl.databinding.BblViewStubLayoutBinding;
import com.sundy.bbl.databinding.ViewStubBinding;
import com.sundy.bbl.mvvm.model.User;


public class ViewStubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BblViewStubLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.bbl_view_stub_layout);
        binding.viewStub.getViewStub().inflate();

        binding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                ViewStubBinding binding = DataBindingUtil.bind(inflated);
                User user = new User("sundy");
                binding.setUser(user);
            }
        });


    }
}
