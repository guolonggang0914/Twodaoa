package com.bway.two.view.IMview.customcallback;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public interface RebateViewItem<T>{
    void  successOn(T t);
    void  errorOn(String message,int code);
}
