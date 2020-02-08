package com.sundy.common;

import android.app.Application;

/**
 * 项目名称：BBLDataBinding
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-18 23:17
 * 描述：
 */
public class App extends Application {
    public static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

}
