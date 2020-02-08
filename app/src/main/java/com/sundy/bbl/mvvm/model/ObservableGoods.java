package com.sundy.bbl.mvvm.model;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableFloat;

public class ObservableGoods {
    private ObservableField<String> name;

    private ObservableFloat price;

    private ObservableField<String> details;

    public ObservableGoods(String name, float price, String details) {
        this.name = new ObservableField<>(name);
        this.price = new ObservableFloat(price);
        this.details = new ObservableField<>(details);
    }


}
