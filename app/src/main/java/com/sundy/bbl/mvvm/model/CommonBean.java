package com.sundy.bbl.mvvm.model;

public class CommonBean {
    private String title;
    private Class clazz;
    public CommonBean(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CommonBean(String title, Class clazz) {
        this.title = title;
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
