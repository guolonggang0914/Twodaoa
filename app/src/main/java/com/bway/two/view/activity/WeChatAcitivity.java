package com.bway.two.view.activity;

import android.app.Application;

import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @类的用途:
 * @姓名: 张惠行
 * @date 2017/8/14
 */

public class WeChatAcitivity  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        registToWX();
    }

    private void registToWX() {
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
//        mWxApi = WXAPIFactory.createWXAPI(this, AppConst.WEIXIN.APP_ID, false);
//        // 将该app注册到微信
//        mWxApi.registerApp(AppConst.WEIXIN.APP_ID);
    }
}
