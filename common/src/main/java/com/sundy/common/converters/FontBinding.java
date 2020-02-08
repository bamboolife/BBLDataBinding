package com.sundy.common.converters;

import android.graphics.Typeface;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;

import com.sundy.common.App;
import com.sundy.common.R;
import com.sundy.common.utils.FontCache;

public class FontBinding {

    @BindingConversion
    public static Typeface convertStringToFace(String fontName){
        try {
            return FontCache.getTypeface(fontName, App.getInstance());
        }catch (Exception e){
            throw e;
        }
    }

    @BindingAdapter("android:text")
    public static void setText(TextView v, String s){
        v.setTypeface(convertStringToFace(App.getInstance().getString(R.string.nutso2)));
        v.setText(s);
    }
}
