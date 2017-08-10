package com.bway.two.view.IMview;

/**
 * 登录接口
 * autor: 李金涛.
 * date:2017/8/9
 */


public interface IMLogin<T> {

    void onSucceed(T t);
    void onError(int code,String err);
    void checkInfo(boolean isNull,String msg);

}
