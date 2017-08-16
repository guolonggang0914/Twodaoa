package com.bway.two.view.IMview.customcallback;

import com.bway.two.presenter.BasePresenter;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public interface RebateView<T>{
    void  onSuceess(T t);
    void onError(String msg,int code);
}
