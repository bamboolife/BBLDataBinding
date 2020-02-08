package com.sundy.bbl.ui.aty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.sundy.bbl.BindConversion;
import com.sundy.bbl.R;
import com.sundy.bbl.mvvm.model.User;

/**
 * @author bamboo
 *@version V1.0
 * @ProjectName: a
 * @ClassName: FontBindingActivity
 * @Package com.sundy.bbl.ui.aty
 * @Description:
 * @date 2020-02-07 22:59
 * @UpdateRemark 更新说明：
 **/
public class FontBindingActivity extends AppCompatActivity {
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     BindConversion binding= DataBindingUtil.setContentView(this,R.layout.bbl_font_binding_layout);
    }
}
