package com.bway.two.model.application;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.bway.two.model.net.okhttp.HttpManager;
import com.bway.two.model.net.okhttp.OkhttpEnginen;

/**
 * autor: 李金涛.
 * date:2017/8/9
 */


public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initManager();
        SDKInitializer.initialize(getApplicationContext());

    }

    private void initManager() {
        HttpManager.getInstance().init(new OkhttpEnginen());
    }
}
