package com.bway.two.model.application;

import android.app.Application;

import com.bway.two.model.net.okhttp.OkhttpEnginen;
import com.bway.two.model.net.okhttp.HttpManager;

/**
 * autor: 李金涛.
 * date:2017/8/9
 */


public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initManager();
    }

    private void initManager() {
        HttpManager.getInstance().init(new OkhttpEnginen());
    }
}
