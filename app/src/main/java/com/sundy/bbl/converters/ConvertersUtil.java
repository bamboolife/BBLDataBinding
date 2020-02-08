package com.sundy.bbl.converters;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import androidx.databinding.BindingConversion;

import com.sundy.bbl.R;

public class ConvertersUtil {
    /**
     *
     * @param color 将16
     * @return
     */
    @BindingConversion
    public static int convertColorToString(int color) {
        switch (color) {
            case Color.RED:
                return R.string.red;
            case Color.WHITE:
                return R.string.white;
        }
        return R.string.app_name;
    }

    /**
     * 将字符串颜色值转化为ColorDrawable
     * @param colorString 如：#ff0000
     * @return
     */
    @BindingConversion
    public static ColorDrawable convertColorToDrawable(String colorString) {
        int color = Color.parseColor(colorString);
        return new ColorDrawable(color);
    }

}
