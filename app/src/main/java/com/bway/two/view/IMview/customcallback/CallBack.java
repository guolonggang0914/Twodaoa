package com.bway.two.view.IMview.customcallback;

/**
 * Created by luccc
 * Create Time: 2017/7/19
 * Description:
 */

public interface CallBack<T> {
    void onSuccess(T t);
    void onFailure(String message, int error);
}
