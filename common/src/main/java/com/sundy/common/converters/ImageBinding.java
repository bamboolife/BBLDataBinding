package com.sundy.common.converters;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class ImageBinding {
    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }
}
