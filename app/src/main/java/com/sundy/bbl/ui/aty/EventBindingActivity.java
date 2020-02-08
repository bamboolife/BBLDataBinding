package com.sundy.bbl.ui.aty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Toast;

import com.sundy.bbl.R;
import com.sundy.bbl.databinding.BblEventBindingLayoutBinding;
import com.sundy.bbl.mvvm.model.User;

/**
 *
 */
public class EventBindingActivity extends AppCompatActivity {
    BblEventBindingLayoutBinding binding;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbl_event_binding_layout);
        binding= DataBindingUtil.setContentView(this,R.layout.bbl_event_binding_layout);
        user=new User("sundy","123456");
        binding.setUserInfo(user);
    }

    public class UserPresenter {

        public void onUserNameClick(User user) {
            Toast.makeText(EventBindingActivity.this, "用户名：" + user.getName(), Toast.LENGTH_SHORT).show();
        }

        public void afterTextChanged(Editable s) {
            user.setName(s.toString());
            binding.setUserInfo(user);
        }

        public void afterUserPasswordChanged(Editable s) {
            user.setPassword(s.toString());
            binding.setUserInfo(user);
        }

    }
}
